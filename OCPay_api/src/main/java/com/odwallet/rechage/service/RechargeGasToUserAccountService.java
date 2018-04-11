package com.odwallet.rechage.service;

import com.odwallet.common.config.InitConfig;
import com.odwallet.common.util.AES;
import com.odwallet.common.util.Constants;
import com.odwallet.common.util.Rand;
import com.odwallet.common.web3j.api.Web3JClient;
import com.odwallet.common.web3j.service.CustomNodeService;
import com.odwallet.common.web3j.transaction.OWalletTransaction;
import com.odwallet.common.web3j.transaction.TransactionOnNode;
import com.odwallet.common.web3j.utils.CommonUtils;
import com.odwallet.rechage.dao.MerchantWalletGroupRelationMapper;
import com.odwallet.rechage.dao.TransactionOrderMapper;
import com.odwallet.rechage.dao.WalletGroupDetailMapper;
import com.odwallet.rechage.dao.WalletGroupMapper;
import com.odwallet.rechage.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fly on 18/3/23.
 */
@Service
public class RechargeGasToUserAccountService {

    Logger logger = LoggerFactory.getLogger(RechargeGasToUserAccountService.class);

    @Autowired
    private CustomNodeService customNodeService;


    @Autowired
    private TransactionOrderMapper transactionOrderMapper;

    @Autowired
    private OWalletTransaction oWalletTransaction;

    @Autowired
    private InitConfig initConfig;

    @Autowired
    private WalletGroupMapper walletGroupMapper;

    @Autowired
    private WalletGroupDetailMapper walletGroupDetailMapper;

    @Autowired
    private MerchantWalletGroupRelationMapper merchantWalletGroupRelationMapper;

    public void rechargeGas(UserCoinBalance userCoinBalance, UserWalletInfo userWalletInfo){
        try {
            String gasPrivateKey = getWalletPrivateKeyByTypeAndMerchantId(1, userCoinBalance.getMerchantId());
            if (StringUtils.isNotBlank(gasPrivateKey)) {
                Web3j web3j = Web3JClient.getClient();
                BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice();
                String privateKey = AES.decrypt(userWalletInfo.getPrivatekey(), initConfig.deskey);
                Credentials credentials = Credentials.create(privateKey);
                BigInteger nonce = web3j.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).send().getTransactionCount();
                String decrypt = AES.decrypt(gasPrivateKey, initConfig.deskey);
                Credentials credentials2 = Credentials.create(decrypt);
                BigInteger nonce2 = web3j.ethGetTransactionCount(credentials2.getAddress(), DefaultBlockParameterName.LATEST).send().getTransactionCount();
                BigInteger needGasLimit = TransactionOnNode.getContractTransactionGasLimit(web3j, credentials.getAddress(), nonce, gasPrice);
                BigInteger ethTransactionGasLimit = TransactionOnNode.getEthTransactionGasLimit(web3j, credentials2.getAddress(), nonce2, gasPrice);
                needGasLimit = needGasLimit.divide(new BigInteger("10")).multiply(new BigInteger("12"));
                BigDecimal needGas = CommonUtils.bit18(needGasLimit.multiply(gasPrice));
                BigDecimal userOddGas = TransactionOnNode.balanceOfETH(web3j, credentials.getAddress());
                if (userOddGas.compareTo(needGas) >= 0) {
                    needGas = Constants.USER_ENOUGH_GAS;
                } else {
                    needGas = needGas.subtract(userOddGas);
                }
                String txHash = oWalletTransaction.doubleTransactionETH(web3j, credentials.getAddress(), credentials2.getEcKeyPair(), needGas.toPlainString(), gasPrice.toString(), ethTransactionGasLimit.toString(), "");
                if (Constants.TRANSFER_ERROR.equals(txHash)) {
                    logger.warn("=============recharge gas error====================================");
                    return;
                }
                if (StringUtils.isNotBlank(txHash)) {
                    TransactionOrder selectByTxHashOrder = transactionOrderMapper.selectByTxHash(txHash);
                    if (selectByTxHashOrder != null) {
                        logger.warn("the order has existed");
                        return;
                    }
                    TransactionOrder transactionOrder = new TransactionOrder();
                    transactionOrder.setTxHash(txHash);
                    transactionOrder.setCoinNum(needGas);
                    transactionOrder.setCoinName(Constants.GAS_COIN_NAME);
                    transactionOrder.setCoinId(Constants.GAS_COIN_ID);
                    transactionOrder.setFromAddress(credentials2.getAddress());
                    transactionOrder.setToAddress(credentials.getAddress());
                    transactionOrder.setCreatedTime(new Date());
                    transactionOrder.setGasPrice(new BigDecimal(gasPrice.toString()));
                    transactionOrder.setOrderStatus(Constants.ORDER_STATUS_PEEDING);
                    transactionOrder.setTranscationType(Constants.ORDER_TYPE_GAS_RECHARGE);
                    transactionOrder.setNonce(nonce2);
                    transactionOrder.setGasLimit(needGasLimit);
                    transactionOrderMapper.insertSelective(transactionOrder);
                }

            }
        }catch (Exception e){
            logger.warn("recharge gas error:"+e.getMessage(),e);

        }



    }

    public String getWalletPrivateKeyByTypeAndMerchantId(int type, Integer merchantId) {
        MerchantWalletGroupRelationExample merchantWalletGroupRelationExample = new MerchantWalletGroupRelationExample();
        merchantWalletGroupRelationExample.or().andMerchantIdEqualTo(merchantId);
        List<MerchantWalletGroupRelation> merchantWalletGroupRelations =
                merchantWalletGroupRelationMapper.selectByExample(merchantWalletGroupRelationExample);
        if (merchantWalletGroupRelations != null && merchantWalletGroupRelations.size() > 0) {
            ArrayList<WalletGroupDetail> list = new ArrayList<>();
            for (MerchantWalletGroupRelation mwgr : merchantWalletGroupRelations) {
                WalletGroup walletGroup = walletGroupMapper.selectByPrimaryKey(mwgr.getWalletGroupId());
                if (walletGroup != null && walletGroup.getGroupType().intValue() == type) {
                    WalletGroupDetailExample walletGroupDetailExample = new WalletGroupDetailExample();
                    walletGroupDetailExample.or().andGroupIdEqualTo(walletGroup.getId());
                    List<WalletGroupDetail> walletGroupDetailList = walletGroupDetailMapper.selectByExample(walletGroupDetailExample);
                    if (walletGroupDetailList != null && walletGroupDetailList.size() > 0) {
                        list.addAll(walletGroupDetailList);
                    }
                }

            }
            if (list.size() > 0) {
                int i = Rand.RandNum(list.size());
                WalletGroupDetail walletGroupDetail = list.get(i);
                return walletGroupDetail.getPrivateKey();
            }
        }
        return null;
    }

}
