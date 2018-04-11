package com.odwallet.rechage.service;

import com.alibaba.fastjson.JSON;
import com.odwallet.common.util.Constants;
import com.odwallet.common.util.DateUtils;
import com.odwallet.common.web3j.bean.TransactionVerificationInfo;
import com.odwallet.common.web3j.utils.CommonUtils;
import com.odwallet.common.web3j.utils.OWalletUtils;
import com.odwallet.rechage.dao.*;
import com.odwallet.rechage.entity.*;
import com.odwallet.rechage.rabbit.RabbitRechargeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.Transaction;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by fly on 18/3/23.
 */
@Service
public class CheckRechargeOrderOnNodeService {

    Logger logger = LoggerFactory.getLogger(CheckRechargeOrderOnNodeService.class);


    @Autowired
    private UserWalletInfoMapper userWalletInfoMapper;

    @Autowired
    private TransactionOrderMapper transactionOrderMapper;

    @Autowired
    private DefeatOrderLogMapper defeatOrderLogMapper;


    @Autowired
    private CoinInfoMapper coinInfoMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private GasTransactionLogMapper gasTransactionLogMapper;


    /**
     * @param transaction
     */
    public void checkNodeRecharge(Transaction transaction) {
        String toAddress = CommonUtils.getContractAddressTo(transaction.getInput());
        UserWalletInfo userWalletInfo = userWalletInfoMapper.selectWalletInfoByAddress(toAddress);
        if (userWalletInfo != null) {
            CoinInfo coinInfo = coinInfoMapper.selectByContractAddress(transaction.getTo());
            TransactionOrder transactionOrder = new TransactionOrder();
            transactionOrder.setCreatedTime(new Date());
            insertTransactionOrder(transaction, toAddress, Constants.ORDER_TYPE_USER_RECHARGE, coinInfo, Constants.ORDER_STATUS_PEEDING, transactionOrder);

        }

    }

    /**
     * @param transaction
     * @param toAddress
     * @param coinInfo
     * @param orderStatus
     */
    public int insertTransactionOrder(Transaction transaction, String toAddress, Integer transcationType, CoinInfo coinInfo, Integer orderStatus, TransactionOrder transcationOrder) {
        TransactionOrder order = transactionOrderMapper.selectByTxHash(transaction.getHash());
        if (order != null) {
            return 0;
        }
        try {
            transcationOrder.setTxHash(transaction.getHash());
            transcationOrder.setTranscationType(transcationType);
            transcationOrder.setCoinName(coinInfo.getCoinName());
            transcationOrder.setCoinId(coinInfo.getId());
            transcationOrder.setCoinNum(CommonUtils.getSTAmount(transaction.getInput()));
            transcationOrder.setCreatedTime(new Date());
            transcationOrder.setGasPrice(new BigDecimal(transaction.getGasPriceRaw()));
            transcationOrder.setFromAddress(transaction.getFrom());
            transcationOrder.setToAddress(toAddress);
            transcationOrder.setOrderStatus(orderStatus);
            transcationOrder.setNonce(transaction.getNonceRaw().startsWith("0x") ? transaction.getNonce() : new BigInteger(transaction.getNonceRaw()));
            return transactionOrderMapper.insertSelective(transcationOrder);
        } catch (Exception e) {
            logger.error("insert order error:"+e.getMessage(),e);
            DefeatOrderLog defeatOrderLog = new DefeatOrderLog();
            defeatOrderLog.setTxHash(transaction.getHash());
            defeatOrderLog.setCreateTime(new Date());
            defeatOrderLog.setReason(Constants.RECHARGE_ORDER_ERROR);
            defeatOrderLogMapper.insertSelective(defeatOrderLog);
            return 0;
        }

    }

    /**
     * confirm order
     *
     * @param transactionOrder
     * @param verificationInfo
     * @throws Exception
     */
    public void confirmOrder(TransactionOrder transactionOrder, TransactionVerificationInfo verificationInfo) throws Exception {
        if (verificationInfo != null && verificationInfo.isVerification()) {
            transactionOrder.setUsedGas(verificationInfo.getGasUsed());
            transactionOrder.setTradingTime(DateUtils.TimeStamp2Date(verificationInfo.getTimeStamp()));
            transactionOrder.setFee(OWalletUtils.getTransactionFee(transactionOrder.getGasPrice(), transactionOrder.getUsedGas()));
            if (Constants.ORDER_TYPE_USER_RECHARGE == transactionOrder.getTranscationType()) {
                rabbitTemplate.convertAndSend(RabbitRechargeConfig.CHECK_BALANCE, JSON.toJSONString(transactionOrder));
            }
            if (Constants.ORDER_TYPE_GAS_RECHARGE == transactionOrder.getTranscationType()) {
                TransactionOrder oldOrder = new TransactionOrder();
                oldOrder.setTxHash(transactionOrder.getTxHash());
                oldOrder.setOrderStatus(Constants.ORDER_STATUS_SUCCESS);
                oldOrder.setUpdatedTime(new Date());
                oldOrder.setTradingTime(DateUtils.TimeStamp2Date(verificationInfo.getTimeStamp()));
                oldOrder.setUsedGas(verificationInfo.getGasUsed());
                oldOrder.setFee(OWalletUtils.getTransactionFee(transactionOrder.getGasPrice(), transactionOrder.getUsedGas()));
                int i = transactionOrderMapper.updateByPrimaryKeyAndOrderStatus(oldOrder);
                if (i > 0) {
                    rabbitTemplate.convertAndSend(RabbitRechargeConfig.RECHARGE_COIN_TO_GATHER_ACCOUNT, JSON.toJSONString(transactionOrder));
                    UserWalletInfo userWalletInfo = userWalletInfoMapper.selectWalletInfoByAddress(transactionOrder.getToAddress());
                    GasTransactionLog gasTransactionLog = new GasTransactionLog();
                    gasTransactionLog.setCoinName(Constants.GAS_COIN_NAME);
                    gasTransactionLog.setCoinNum(transactionOrder.getCoinNum());
                    gasTransactionLog.setGasFee(transactionOrder.getFee());
                    gasTransactionLog.setCreatedAt(new Date());
                    gasTransactionLog.setOrderTxHash(transactionOrder.getTxHash());
                    gasTransactionLog.setFromAddress(transactionOrder.getFromAddress());
                    gasTransactionLog.setToAddress(transactionOrder.getToAddress());
                    gasTransactionLog.setNonce(transactionOrder.getNonce());
                    gasTransactionLog.setUserid(userWalletInfo.getUserid());
                    gasTransactionLog.setMerchantId(userWalletInfo.getMerchantId());
                    gasTransactionLogMapper.insertSelective(gasTransactionLog);
                }

            }
            //recharge order
            if (Constants.ORDER_TYPE_GATHER_RECHARGE == transactionOrder.getTranscationType()) {
                rabbitTemplate.convertAndSend(RabbitRechargeConfig.AFFIRM_RECHARGE_TO_GATHER, JSON.toJSONString(transactionOrder));
            }
            //transfer
            if (Constants.ORDER_TYPE_PLATFORM_TRANSFER == transactionOrder.getTranscationType()) {
                rabbitTemplate.convertAndSend(RabbitRechargeConfig.AFFIRM_PLATFORM_TRANSFER, JSON.toJSONString(transactionOrder));
            }

        }
    }


}
