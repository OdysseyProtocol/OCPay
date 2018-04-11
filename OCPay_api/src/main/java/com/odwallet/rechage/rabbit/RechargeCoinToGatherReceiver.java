package com.odwallet.rechage.rabbit;

import com.alibaba.fastjson.JSON;
import com.odwallet.rechage.entity.*;
import com.odwallet.rechage.service.RechargeCoinToGatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RechargeCoinToGatherReceiver {

    Logger logger = LoggerFactory.getLogger(RechargeCoinToGatherReceiver.class);

    @Autowired
    private RechargeCoinToGatherService rechargeCoinToGatherService;


    @RabbitListener(queues = RabbitRechargeConfig.RECHARGE_COIN_TO_GATHER_ACCOUNT)
    public void rechargeToGeneralLedger(String msg) {
            logger.warn("gas has reached,start recharge coin to wallet: "+ msg);
            TransactionOrder transactionOrder = JSON.parseObject(msg, TransactionOrder.class);
            rechargeCoinToGatherService.rechargeCoin(transactionOrder);


    }



}



