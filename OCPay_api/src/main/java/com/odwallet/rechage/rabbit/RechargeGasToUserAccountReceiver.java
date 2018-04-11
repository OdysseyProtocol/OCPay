package com.odwallet.rechage.rabbit;

import com.alibaba.fastjson.JSON;
import com.odwallet.rechage.entity.*;
import com.odwallet.rechage.service.RechargeGasToUserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class
RechargeGasToUserAccountReceiver {

    private static Logger logger = LoggerFactory.getLogger(RechargeGasToUserAccountReceiver.class);

    @Autowired
    private RechargeGasToUserAccountService rechargeGasToUserAccountService;



    @RabbitListener(queues = RabbitRechargeConfig.RECHARGE_GAS_TO_USER_ACCOUNT)
    public void rechargeToGeneralLedger(String msg) {
        TransactionMsg transactionMsg = JSON.parseObject(msg, TransactionMsg.class);
        UserCoinBalance userCoinBalance = transactionMsg.getUserCoinBalance();
        UserWalletInfo userWalletInfo = transactionMsg.getUserWalletInfo();
        rechargeGasToUserAccountService.rechargeGas(userCoinBalance,userWalletInfo);

    }





}
