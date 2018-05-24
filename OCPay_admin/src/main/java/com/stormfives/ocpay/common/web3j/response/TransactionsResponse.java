package com.stormfives.ocpay.common.web3j.response;

import org.web3j.protocol.core.methods.response.Transaction;

import java.math.BigInteger;
import java.util.List;

public class TransactionsResponse {

    public TransactionsResponse() {
    }

    private String status;
    private String message;
    private List<CustomTransaction> result;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setResult(List<CustomTransaction> result) {
        this.result = result;
    }

    public List<CustomTransaction> getResult() {
        return result;
    }


    public static class CustomTransaction extends Transaction {

        private String timeStamp;
        private String isError;
        private String txreceipt_status;
        private String contractAddress;
        private String cumulativeGasUsed;
        private String gasUsed;
        private String confirmations;


        public CustomTransaction() {
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getTimeStamp() {
            return timeStamp;
        }


        public void setIsError(String isError) {
            this.isError = isError;
        }

        public String getIsError() {
            return isError;
        }

        public void setTxreceipt_status(String txreceipt_status) {
            this.txreceipt_status = txreceipt_status;
        }

        public String getTxreceipt_status() {
            return txreceipt_status;
        }


        public void setContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
        }

        public String getContractAddress() {
            return contractAddress;
        }

        public void setCumulativeGasUsed(String cumulativeGasUsed) {
            this.cumulativeGasUsed = cumulativeGasUsed;
        }

        public String getCumulativeGasUsed() {
            return cumulativeGasUsed;
        }

        public void setGasUsed(String gasUsed) {
            this.gasUsed = gasUsed;
        }

        public String getGasUsed() {
            return gasUsed;
        }

        public void setConfirmations(String confirmations) {
            this.confirmations = confirmations;
        }

        public String getConfirmations() {
            return confirmations;
        }

        @Override
        public BigInteger getBlockNumber() {
            return new BigInteger(getBlockNumberRaw());
        }

        @Override
        public BigInteger getGas() {
            return new BigInteger(getGasRaw());
        }

        @Override
        public BigInteger getGasPrice() {
            return new BigInteger(getGasPriceRaw());
        }

        @Override
        public BigInteger getNonce() {
            return new BigInteger(getNonceRaw());
        }

        @Override

        public BigInteger getTransactionIndex() {
            return new BigInteger(getTransactionIndexRaw());
        }

        @Override


        public BigInteger getValue() {
            return new BigInteger(getValueRaw());
        }


    }

}