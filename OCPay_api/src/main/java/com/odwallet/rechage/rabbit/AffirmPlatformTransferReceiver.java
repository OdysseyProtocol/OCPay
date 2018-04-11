package com.odwallet.rechage.rabbit;

import com.alibaba.fastjson.JSON;
import com.odwallet.rechage.entity.TransactionOrder;
import com.odwallet.rechage.service.AffirmPlatformTransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by fly on 18/3/24.
 */
@Component
public class AffirmPlatformTransferReceiver {
    Logger logger = LoggerFactory.getLogger(AffirmPlatformTransferReceiver.class);
    @Autowired
    private AffirmPlatformTransferService affirmPlatformTransferService;
    @RabbitListener(queues = RabbitRechargeConfig.AFFIRM_PLATFORM_TRANSFER)
    public void rechargeToGeneralLedger(String msg) {
            TransactionOrder transactionOrder = JSON.parseObject(msg, TransactionOrder.class);
            affirmPlatformTransferService.affirmPlatformTransfer(transactionOrder);
    }
}
