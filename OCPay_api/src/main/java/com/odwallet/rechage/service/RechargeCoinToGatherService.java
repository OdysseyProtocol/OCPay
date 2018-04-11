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
import com.odwallet.rechage.dao.*;
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
public class RechargeCoinToGatherService {
    Logger logger = LoggerFactory.getLogger(RechargeCoinToGatherService.class);
    @Autowired
    private CustomNodeService customNodeService;
    @Autowired
    private UserWalletInfoMapper userWalletInfoMapper;
    @Autowired
    private UserCoinBalanceMapper userCoinBalanceMapper;
    @Autowired
    private CoinInfoMapper coinInfoMapper;
    @Autowired
    private OWalletTransaction oWalletTransaction;
    @Autowired
    private TransactionOrderMapper transactionOrderMapper;
    @Autowired
    private InitConfig initConfig;
    @Autowired
    private WalletGroupMapper walletGroupMapper;
    @Autowired
    private WalletGroupDetailMapper walletGroupDetailMapper;
    @Autowired
    private MerchantWalletGroupRelationMapper merchantWalletGroupRelationMapper;

    public void rechargeCoin(TransactionOrder transactionOrder) {
        UserWalletInfo userWalletInfo = userWalletInfoMapper.selectWalletInfoByAddress(transactionOrder.getToAddress());
        UserCoinBalance userCoinBalance = userCoinBalanceMapper.selectByUserIdAndMerchantInfoId(userWalletInfo.getUserid(), userWalletInfo.getMerchantId());
        CoinInfo coinInfo = coinInfoMapper.selectByPrimaryKey(userCoinBalance.getCoinId());
        Date toWalletTime = userCoinBalance.getToWalletTime();
        if (toWalletTime == null) {
            toWalletTime = new Date();
        }
        if (userCoinBalance.getTransferStatus() == Constants.USER_IN_TRANS && (new Date().getTime() - toWalletTime.getTime()) < 1000 * 600) {
            logger.warn("in transfer");
            return;
        }
        String address = getWalletAddressByTypeAndMerchantId(2, userCoinBalance.getMerchantId());//提币地址
        if (StringUtils.isNotBlank(address)) {
            Web3j web3j = Web3JClient.getClient();
            BigDecimal gasPrice = transactionOrder.getGasPrice();
            BigInteger gasPriceInteger = new BigInteger(gasPrice.toString().substring(0, gasPrice.toString().length() - 19));
            BigInteger gasLimit = transactionOrder.getGasLimit() == null ? new BigInteger("60000") : transactionOrder.getGasLimit();
            BigInteger nonce = null;
            try {
                nonce = web3j.ethGetTransactionCount(address, DefaultBlockParameterName.LATEST).send().getTransactionCount();
            } catch (Exception e) {
                logger.error("web3j get nonce error:" + e.getMessage(), e);
            }
            String privateKey = AES.decrypt(userWalletInfo.getPrivatekey(), initConfig.deskey);
            Credentials credentials = Credentials.create(privateKey);
            BigDecimal userWalletOCN = TransactionOnNode.balanceOfContractToken(web3j, coinInfo.getContractAddress(), credentials.getAddress());
            BigDecimal userOCN = CommonUtils.bit18(new BigInteger(userWalletOCN.toString()));
            if (userOCN.compareTo(new BigDecimal(coinInfo.getCoinLowerLimit().toString())) < 0) {
                logger.warn("balance not enough：" + userCoinBalance.getCoinBalance());
                return;
            }
            String txHash = oWalletTransaction.doubleTransactionCoin(web3j, address, credentials.getEcKeyPair(), userOCN.toString(), gasPriceInteger.toString(), gasLimit.toString(), coinInfo.getContractAddress(), "");
            if (StringUtils.isNotBlank(txHash)) {
                UserCoinBalance coinBalance = new UserCoinBalance();
                if (Constants.TRANSFER_ERROR.equals(txHash)) {
                    coinBalance.setId(userCoinBalance.getId());
                    coinBalance.setTransferStatus((byte) Constants.USER_GAS_ACQUIRE);
                    userCoinBalanceMapper.updateByPrimaryKeySelective(coinBalance);
                } else {
                    coinBalance.setId(userCoinBalance.getId());
                    coinBalance.setTransferStatus((byte) Constants.USER_IN_TRANS);
                    coinBalance.setToWalletTime(new Date());
                    userCoinBalanceMapper.updateByPrimaryKeySelective(coinBalance);
                    TransactionOrder rechargeCoinOrder = new TransactionOrder();
                    rechargeCoinOrder.setTxHash(txHash);
                    rechargeCoinOrder.setCoinNum(userOCN);
                    rechargeCoinOrder.setCoinName(coinInfo.getCoinName());
                    rechargeCoinOrder.setCoinId(coinInfo.getId());
                    rechargeCoinOrder.setFromAddress(credentials.getAddress());
                    rechargeCoinOrder.setToAddress(address);
                    rechargeCoinOrder.setNonce(nonce);
                    rechargeCoinOrder.setCreatedTime(new Date());
                    rechargeCoinOrder.setGasPrice(new BigDecimal(gasPrice.toString()));
                    rechargeCoinOrder.setOrderStatus(Constants.ORDER_STATUS_PEEDING);
                    rechargeCoinOrder.setTranscationType(Constants.ORDER_TYPE_GATHER_RECHARGE);
                    transactionOrderMapper.insertSelective(rechargeCoinOrder);
                }
            }
        }
    }

    public String getWalletAddressByTypeAndMerchantId(int type, Integer merchantId) {
        MerchantWalletGroupRelationExample merchantWalletGroupRelationExample = new MerchantWalletGroupRelationExample();
        merchantWalletGroupRelationExample.or().andMerchantIdEqualTo(merchantId);
        List<MerchantWalletGroupRelation> merchantWalletGroupRelations =
                merchantWalletGroupRelationMapper.selectByExample(merchantWalletGroupRelationExample);
        if (merchantWalletGroupRelations != null && merchantWalletGroupRelations.size() > 0) {
            ArrayList<String> list = new ArrayList<>();
            for (MerchantWalletGroupRelation mwgr : merchantWalletGroupRelations) {
                WalletGroup walletGroup = walletGroupMapper.selectByPrimaryKey(mwgr.getWalletGroupId());
                if (walletGroup != null && walletGroup.getGroupType().intValue() == type) {
                    WalletGroupDetailExample walletGroupDetailExample = new WalletGroupDetailExample();
                    walletGroupDetailExample.or().andGroupIdEqualTo(walletGroup.getId());
                    List<WalletGroupDetail> walletGroupDetailList = walletGroupDetailMapper.selectByExample(walletGroupDetailExample);
                    if (walletGroupDetailList != null && walletGroupDetailList.size() > 0) {
                        for (WalletGroupDetail wgd : walletGroupDetailList)
                            list.add(wgd.getWalletAddress());
                    }
                }

            }
            if (list.size() > 0) {
                int i = Rand.RandNum(list.size());
                String s = list.get(i);
                return s;
            }
        }
        return null;
    }


}
