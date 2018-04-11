package com.odwallet.rechage.service;

import com.alibaba.fastjson.JSON;
import com.odwallet.common.util.Constants;
import com.odwallet.common.util.DateUtils;
import com.odwallet.common.web3j.api.OWalletAPI;
import com.odwallet.common.web3j.response.TransactionsResponse;
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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by fly on 18/3/23.
 */
@Service
public class CheckRechargeOrderOnScanService {

    Logger logger = LoggerFactory.getLogger(CheckRechargeOrderOnScanService.class);


    @Autowired
    private ScheduleBlockNumMapper scheduleBlockNumMapper;

    @Autowired
    private CheckSuccessRechargeOrderService checkSuccessRechargeOrderService;

    @Autowired
    private CheckRechargeOrderOnNodeService checkRechargeOrderOnNodeService;


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

    public void scanBlockTransaction() {
        CoinInfoExample infoExample = new CoinInfoExample();
        infoExample.or().andContractAddressNotEqualTo("").andContractAddressIsNotNull();
        List<CoinInfo> coinInfos = coinInfoMapper.selectByExample(infoExample);
        ScheduleBlockNumExample scheduleBlockNumExample = new ScheduleBlockNumExample();
        for (CoinInfo coinInfo : coinInfos) {
            try {
                scheduleBlockNumExample.clear();
                ScheduleBlockNumExample.Criteria criteria = scheduleBlockNumExample.createCriteria();
                criteria.andCoinIdEqualTo(coinInfo.getId());
                BigInteger recentBlockNumber = OWalletAPI.getRecentBlockNumber();
                List<ScheduleBlockNum> scheduleBlockNa = scheduleBlockNumMapper.selectByExample(scheduleBlockNumExample);
                ScheduleBlockNum blockNum = (scheduleBlockNa == null || scheduleBlockNa.size() <= 0) ? null : scheduleBlockNa.get(0);
                if (blockNum == null) {
                    blockNum = new ScheduleBlockNum();
                    blockNum.setStartBlockNum(recentBlockNumber.longValue());
                    blockNum.setEndBlockNum(recentBlockNumber.longValue());
                    blockNum.setCoinId(coinInfo.getId());
                    scheduleBlockNumMapper.insertSelective(blockNum);
                } else {
                    Long confirmBlockNumber = recentBlockNumber.longValue();
                    Long fromBlockNumber = blockNum.getEndBlockNum() + 1;
                    Long endBlockNumber = confirmBlockNumber - 12;
                    if (endBlockNumber > blockNum.getEndBlockNum()) {
                        List<TransactionsResponse.CustomTransaction> transactionList = OWalletAPI.getTransactionList(coinInfo.getContractAddress(), fromBlockNumber.toString(), endBlockNumber.toString());
                        ScanBlockInfo scanBlockInfo = new ScanBlockInfo();
                        blockNum.setStartBlockNum(fromBlockNumber);
                        blockNum.setEndBlockNum(endBlockNumber);
                        blockNum.setLastScanTime(new Date());
                        scanBlockInfo.setScheduleBlockNum(blockNum);
                        scanBlockInfo.setTransactionList(transactionList);
                        rabbitTemplate.convertAndSend(RabbitRechargeConfig.SCAN_BLOCK_ORDER, JSON.toJSONString(scanBlockInfo));
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }


        }
    }

    /**
     * @param scanBlockInfo
     */
    public void checkScanOrder(ScanBlockInfo scanBlockInfo) throws Exception {
        if (scanBlockInfo.getTransactionList() != null && scanBlockInfo.getTransactionList().size() > 0) {
            for (TransactionsResponse.CustomTransaction transaction : scanBlockInfo.getTransactionList()) {
                String toAddress = CommonUtils.getContractAddressTo(transaction.getInput());
                UserWalletInfo userWalletInfo = userWalletInfoMapper.selectWalletInfoByAddress(toAddress);
                if (userWalletInfo != null) {
                    if (Constants.RECHARGE_ORDER_SUCCESS.equals(transaction.getTxreceipt_status())) {
                        TransactionOrder transactionOrder = transactionOrderMapper.selectByTxHash(transaction.getHash());
                        if (transactionOrder != null) {
                            if (Constants.ORDER_STATUS_PEEDING == transactionOrder.getOrderStatus()) {
                                setTrandTimeAndFee(transaction, transactionOrder);
                                int i = checkSuccessRechargeOrderService.changeTransactionSuccess(transactionOrder);
                                updateUserBalanceAndRecordLog(userWalletInfo, transactionOrder, i);
                            }
                        } else {
                            transactionOrder = new TransactionOrder();
                            setTrandTimeAndFee(transaction, transactionOrder);
                            CoinInfo coinInfo = coinInfoMapper.selectByContractAddress(transaction.getTo());
                            int i = checkRechargeOrderOnNodeService.insertTransactionOrder(transaction, toAddress, Constants.ORDER_TYPE_USER_RECHARGE, coinInfo, Constants.ORDER_STATUS_SUCCESS, transactionOrder);
                            updateUserBalanceAndRecordLog(userWalletInfo, transactionOrder, i);
                        }
                    } else {

                        DefeatOrderLog defeatOrderLog = new DefeatOrderLog();
                        defeatOrderLog.setTxHash(transaction.getHash());
                        defeatOrderLog.setCreateTime(new Date());
                        defeatOrderLog.setReason(Constants.RECHARGE_ORDER_ERROR);
                        defeatOrderLogMapper.insertSelective(defeatOrderLog);
                    }
                }

            }
        }
    }


    private void setTrandTimeAndFee(TransactionsResponse.CustomTransaction transaction, TransactionOrder transactionOrder) throws ParseException {
        transactionOrder.setUsedGas(new BigDecimal(transaction.getGasUsed()));
        transactionOrder.setTradingTime(DateUtils.TimeStamp2Date(Long.valueOf(transaction.getTimeStamp())));
        transactionOrder.setFee(OWalletUtils.getTransactionFee(new BigDecimal(transaction.getGasPrice().toString()), transactionOrder.getUsedGas()));
    }

    private void updateUserBalanceAndRecordLog(UserWalletInfo userWalletInfo, TransactionOrder transactionOrder, int i) throws Exception {
        if (i > 0) {
            checkSuccessRechargeOrderService.confirmRecharge(transactionOrder, userWalletInfo, transactionOrder.getTxHash());
        }
    }

}
