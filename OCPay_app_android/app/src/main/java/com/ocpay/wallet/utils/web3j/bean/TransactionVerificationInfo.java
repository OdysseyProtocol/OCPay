package com.ocpay.wallet.utils.web3j.bean;

import java.math.BigDecimal;

/**
 * Created by y on 2018/3/10.
 */
public class TransactionVerificationInfo {

    Boolean isVerification;

    Long timeStamp;

    BigDecimal gasUsed;

    public TransactionVerificationInfo(boolean isVerification, long timeStamp, BigDecimal gasUsed) {
        this.isVerification = isVerification;
        this.timeStamp = timeStamp;
        this.gasUsed = gasUsed;
    }

    public TransactionVerificationInfo(boolean isVerification, long timeStamp) {
        this.isVerification = isVerification;
        this.timeStamp = timeStamp;
    }


    public BigDecimal getGasUsed() {
        return gasUsed;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public Boolean isVerification() {
        return isVerification;
    }

    public void setVerification(Boolean verification) {
        isVerification = verification;
    }
}
