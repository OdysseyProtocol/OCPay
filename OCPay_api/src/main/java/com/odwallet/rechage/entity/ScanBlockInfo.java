package com.odwallet.rechage.entity;

import com.odwallet.common.web3j.response.TransactionsResponse;

import java.util.List;

/**
 * Created by y on 2018/3/19.
 */
public class ScanBlockInfo {

    List<TransactionsResponse.CustomTransaction> transactionList;

    ScheduleBlockNum scheduleBlockNum;





    public List<TransactionsResponse.CustomTransaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<TransactionsResponse.CustomTransaction> transactionList) {
        this.transactionList = transactionList;
    }

    public ScheduleBlockNum getScheduleBlockNum() {
        return scheduleBlockNum;
    }

    public void setScheduleBlockNum(ScheduleBlockNum scheduleBlockNum) {
        this.scheduleBlockNum = scheduleBlockNum;
    }
}
