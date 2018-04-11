package com.odwallet.rechage.schedule;

import com.odwallet.common.util.Constants;
import com.odwallet.common.web3j.bean.TransactionVerificationInfo;
import com.odwallet.common.web3j.transaction.OWalletTransaction;
import com.odwallet.rechage.dao.TransactionOrderMapper;
import com.odwallet.rechage.entity.*;
import com.odwallet.rechage.service.CheckRechargeOrderOnNodeService;
import com.odwallet.rechage.service.CheckRechargeOrderOnScanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckBalanceTask {

    @Autowired
    private CheckRechargeOrderOnScanService checkRechargeOrderOnScanService;

    @Autowired
    private TransactionOrderMapper transactionOrderMapper;
    @Autowired
    private CheckRechargeOrderOnNodeService checkRechargeOrderOnNodeService;


    protected Logger logger = LoggerFactory.getLogger(CheckBalanceTask.class);


    @Scheduled(cron = "0 */2 * * * ?")
    public void checkOrderStausFromNode() {
        List<TransactionOrder> transactionOrders = transactionOrderMapper.selectUnConfirmOrder(Constants.NODE_UNCONFIRM_ROW);
        if (transactionOrders != null) {
            for (TransactionOrder transactionOrder : transactionOrders) {
                try {
                    TransactionVerificationInfo verificationInfo = OWalletTransaction.doubleVerifyTransaction(transactionOrder.getTxHash());
                    checkRechargeOrderOnNodeService.confirmOrder(transactionOrder, verificationInfo);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }

            }

        }


    }


    @Scheduled(cron = "0 */3 * * * ?")
    public void scanBlockTranscation() {
        checkRechargeOrderOnScanService.scanBlockTransaction();
    }
}