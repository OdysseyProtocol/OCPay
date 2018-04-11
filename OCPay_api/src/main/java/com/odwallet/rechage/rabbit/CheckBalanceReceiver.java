package com.odwallet.rechage.rabbit;

import com.alibaba.fastjson.JSON;
import com.odwallet.rechage.entity.*;
import com.odwallet.rechage.service.CheckSuccessRechargeOrderService;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CheckBalanceReceiver {
    Logger logger = org.slf4j.LoggerFactory.getLogger(CheckBalanceReceiver.class);
    @Autowired
    private CheckSuccessRechargeOrderService checkSuccessRechargeOrderService;

    /**
     * @param msg
     */
    @RabbitListener(queues = RabbitRechargeConfig.CHECK_BALANCE,containerFactory = "myConnectionFactory")
    public void coinCharge(String msg) {
            TransactionOrder transactionOrder = JSON.parseObject(msg, TransactionOrder.class);
            checkSuccessRechargeOrderService.checkRechargeOrder(transactionOrder);
    }




}
