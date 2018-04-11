package com.odwallet.rechage.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.odwallet.common.apisecurity.AESCBC;
import com.odwallet.common.apisecurity.AESCBCUtil;
import com.odwallet.common.util.Constants;
import com.odwallet.common.util.HttpClient;
import com.odwallet.rechage.dao.*;
import com.odwallet.rechage.entity.*;
import com.odwallet.rechage.rabbit.RabbitRechargeConfig;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fly on 18/3/23.
 */
@Service
public class CheckSuccessRechargeOrderService {
    Logger logger = org.slf4j.LoggerFactory.getLogger(CheckSuccessRechargeOrderService.class);
    @Autowired
    private UserWalletInfoMapper userWalletInfoMapper;
    @Autowired
    private UserCoinBalanceMapper userCoinBalanceMapper;
    @Autowired
    private CoinInfoMapper coinInfoMapper;
    @Autowired
    private UserCoinLogMapper userCoinLogMapper;
    @Autowired
    private TransactionOrderMapper transactionOrderMapper;
    @Autowired
    private MerchantInfoService merchantInfoService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    public void checkRechargeOrder(TransactionOrder transactionOrder)  {
        UserWalletInfo userWalletInfo = userWalletInfoMapper.selectWalletInfoByAddress(transactionOrder.getToAddress());
        String txHash = transactionOrder.getTxHash();
        if (userWalletInfo != null) {
            int res = this.changeTransactionSuccess(transactionOrder);
            if (res > 0) {
                confirmRecharge(transactionOrder, userWalletInfo, txHash);
            }
        } else {
            this.changeTransactionSuccess(transactionOrder);
        }
    }


    /**
     * @param transactionOrder
     */
    public int changeTransactionSuccess(TransactionOrder transactionOrder) {
        TransactionOrder updateOrder = new TransactionOrder();
        updateOrder.setTxHash(transactionOrder.getTxHash());
        updateOrder.setUsedGas(transactionOrder.getUsedGas());
        updateOrder.setTradingTime(transactionOrder.getTradingTime());
        updateOrder.setFee(transactionOrder.getFee());
        updateOrder.setOrderStatus(Constants.ORDER_STATUS_SUCCESS);
        updateOrder.setUpdatedTime(new Date());
        return transactionOrderMapper.updateByPrimaryKeyAndOrderStatus(updateOrder);

    }


    public void confirmRecharge(TransactionOrder transactionOrder, UserWalletInfo userWalletInfo, String txHash)   {
        UserCoinBalance userCoinBalance = getUserCoinBalance(transactionOrder, userWalletInfo, txHash);
        meetConditionsUserCoinBalance(transactionOrder, userWalletInfo, userCoinBalance);
        callBack(transactionOrder, userWalletInfo, userCoinBalance, transactionOrder.getCoinNum());
    }


    /**
     * @param transactionOrder
     * @param userWalletInfo
     * @param userCoinBalance
     */
    private void meetConditionsUserCoinBalance(TransactionOrder transactionOrder, UserWalletInfo userWalletInfo, UserCoinBalance userCoinBalance) {
        CoinInfo coinInfo = coinInfoMapper.selectByPrimaryKey(transactionOrder.getCoinId());
        if (new BigDecimal(coinInfo.getCoinHigherLimit().toString()).compareTo(userCoinBalance.getCoinBalance()) <= 0) {
            TransactionMsg transactionMsg = new TransactionMsg();
            transactionMsg.setUserCoinBalance(userCoinBalance);
            transactionMsg.setUserWalletInfo(userWalletInfo);
            String toTransferMsg = JSON.toJSONString(transactionMsg);
            rabbitTemplate.convertAndSend(RabbitRechargeConfig.RECHARGE_GAS_TO_USER_ACCOUNT, toTransferMsg);
        }
    }

    /**
     * @param transactionOrder
     * @param userWalletInfo
     * @param txHash
     * @return
     */
    private UserCoinBalance getUserCoinBalance(TransactionOrder transactionOrder, UserWalletInfo userWalletInfo, String txHash) {
        UserCoinBalance userCoinBalance = userCoinBalanceMapper.selectByUserIdAndMerchantInfoId(userWalletInfo.getUserid(), userWalletInfo.getMerchantId());
        CoinInfo coinInfo = coinInfoMapper.selectByPrimaryKey(transactionOrder.getCoinId());
        BigDecimal coinNum = transactionOrder.getCoinNum();
        UserCoinLog userCoinLog = new UserCoinLog();
        if (userCoinBalance == null) {
            userCoinBalance = new UserCoinBalance();
            userCoinBalance.setUserid(userWalletInfo.getUserid());
            userCoinBalance.setMerchantId(userWalletInfo.getMerchantId());
            userCoinBalance.setCoinBalance(coinNum);
            userCoinBalance.setShowBalance(coinNum);
            userCoinBalance.setTotalAmount(coinNum);
            userCoinBalance.setCoinName(coinInfo.getCoinName());
            userCoinBalance.setLastTradingTime(new Date());
            userCoinBalance.setCoinId(coinInfo.getId());
            userCoinBalance.setCreateTime(new Date());
            userCoinBalanceMapper.insertSelective(userCoinBalance);
            createUserCoinLog(txHash, coinNum, coinInfo, userCoinBalance, userCoinLog, Constants.USER_COIN_RECHARGE);
        } else {
            userCoinBalance.setCoinBalance(userCoinBalance.getCoinBalance().add(coinNum));
            userCoinBalance.setShowBalance(userCoinBalance.getShowBalance().add(coinNum));
            userCoinBalance.setTotalAmount(userCoinBalance.getTotalAmount().add(coinNum));
            userCoinBalance.setLastTradingTime(new Date());
            userCoinBalanceMapper.updateByPrimaryKeySelective(userCoinBalance);
            createUserCoinLog(txHash, coinNum, coinInfo, userCoinBalance, userCoinLog, Constants.USER_COIN_RECHARGE);
        }
        return userCoinBalance;
    }

    /**
     * @param transactionOrder
     * @param userWalletInfo
     * @param userCoinBalance
     * @param coinNum
     * @throws IOException
     */
    private void callBack(TransactionOrder transactionOrder, UserWalletInfo userWalletInfo, UserCoinBalance userCoinBalance, BigDecimal coinNum)  {
        MerchantInfo merchantInfo = merchantInfoService.getMerchantInfoById(userCoinBalance.getMerchantId());
        JSONObject callBackJSONObject = new JSONObject();
        callBackJSONObject.put("from", transactionOrder.getFromAddress());
        callBackJSONObject.put("to", transactionOrder.getToAddress());
        callBackJSONObject.put("userid", userWalletInfo.getUserid());
        callBackJSONObject.put("coinNum", coinNum);
        callBackJSONObject.put("fee", transactionOrder.getFee());
        callBackJSONObject.put("tradingTime", transactionOrder.getTradingTime() == null ? transactionOrder.getCreatedTime().getTime() : transactionOrder.getTradingTime().getTime());
        callBackJSONObject.put("txHash", transactionOrder.getTxHash());

        toCallBack(transactionOrder, merchantInfo, merchantInfo.getRechargeSuccessUrl(), callBackJSONObject);

    }

    public void toCallBack(TransactionOrder transactionOrder, MerchantInfo merchantInfo, String url, JSONObject callBackJSONObject) {
        try{
            String seed = AESCBC.getRandomStringByLength(16);
            String in = AESCBCUtil.encrypt(callBackJSONObject, merchantInfo.getMerchantName(), merchantInfo.getApikey(), merchantInfo.getSecurity(), seed);
            Map map = new HashMap();
            map.put("in", in);
            map.put("seed", seed);
            HttpClient client = new HttpClient(url, "post");
            client.setEntity(map);
            TransactionOrder order = new TransactionOrder();
            order.setCallbackTime(new Date());
            recordCallbackStatus(transactionOrder, order, Constants.ORDER_CALLBACK_PROCESS);
            String s = client.request(url, "utf-8");
            JSONObject jsonObject = JSON.parseObject(s);
            while (!jsonObject.getBoolean("success") && (int) jsonObject.get("errorCode") != Constants.NOT_REPEATED_CALL_BACK_CODE) {
                Thread.sleep(300000);
                s = client.request(url, "utf-8");
                jsonObject = JSON.parseObject(s);
            }
            if (jsonObject.getBoolean("success")) {
                order = new TransactionOrder();
                recordCallbackStatus(transactionOrder, order, Constants.ORDER_CALLBACK_SUCCESS);

            }
        }catch (Exception e){
            logger.error("call back  error:"+e.getMessage(),e);
        }
    }

    /**
     * @param transactionOrder
     * @param order
     * @param callbacStatus
     */
    private void recordCallbackStatus(TransactionOrder transactionOrder, TransactionOrder order, int callbacStatus) {
        order.setId(transactionOrder.getId());
        order.setCallbackStatus(callbacStatus);
        transactionOrderMapper.updateByPrimaryKeySelective(order);
    }


    /**
     * @param rechargeAmount
     * @param userCoinBalance
     * @param userCoinLog
     * @param chargeType
     */
    private void createUserCoinLog(String txHash, BigDecimal rechargeAmount, CoinInfo coinInfo, UserCoinBalance userCoinBalance, UserCoinLog userCoinLog, Integer chargeType) {
        userCoinLog.setOrderTxHash(txHash);
        userCoinLog.setCoinName(coinInfo.getCoinName());
        userCoinLog.setCoinId(coinInfo.getId());
        userCoinLog.setChangeNum(rechargeAmount);
        userCoinLog.setChangeType(chargeType);
        userCoinLog.setMerchantId(userCoinBalance.getMerchantId());
        userCoinLog.setUserid(userCoinBalance.getUserid());
        userCoinLog.setCreateTime(new Date());
        userCoinLogMapper.insertSelective(userCoinLog);
    }



}
