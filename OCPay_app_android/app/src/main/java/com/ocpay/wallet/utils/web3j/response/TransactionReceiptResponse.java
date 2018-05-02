package com.ocpay.wallet.utils.web3j.response;

import java.util.List;

public class TransactionReceiptResponse {

    private String jsonrpc;
    private int id;
    private Result result;

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }


    public static class Logs {

        private String address;
        private List<String> topics;
        private String data;
        private String blockNumber;
        private String transactionHash;
        private String transactionIndex;
        private String blockHash;
        private String logIndex;
        private boolean removed;

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddress() {
            return address;
        }

        public void setTopics(List<String> topics) {
            this.topics = topics;
        }

        public List<String> getTopics() {
            return topics;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }

        public void setBlockNumber(String blockNumber) {
            this.blockNumber = blockNumber;
        }

        public String getBlockNumber() {
            return blockNumber;
        }

        public void setTransactionHash(String transactionHash) {
            this.transactionHash = transactionHash;
        }

        public String getTransactionHash() {
            return transactionHash;
        }

        public void setTransactionIndex(String transactionIndex) {
            this.transactionIndex = transactionIndex;
        }

        public String getTransactionIndex() {
            return transactionIndex;
        }

        public void setBlockHash(String blockHash) {
            this.blockHash = blockHash;
        }

        public String getBlockHash() {
            return blockHash;
        }

        public void setLogIndex(String logIndex) {
            this.logIndex = logIndex;
        }

        public String getLogIndex() {
            return logIndex;
        }

        public void setRemoved(boolean removed) {
            this.removed = removed;
        }

        public boolean getRemoved() {
            return removed;
        }

    }


    public static class Result {

        private String blockHash;
        private String blockNumber;
        private String contractAddress;
        private String cumulativeGasUsed;
        private String from;
        private String gasUsed;
        private List<Logs> logs;
        private String logsBloom;
        private String status;
        private String to;
        private String transactionHash;
        private String transactionIndex;

        public void setBlockHash(String blockHash) {
            this.blockHash = blockHash;
        }

        public String getBlockHash() {
            return blockHash;
        }

        public void setBlockNumber(String blockNumber) {
            this.blockNumber = blockNumber;
        }

        public String getBlockNumber() {
            return blockNumber;
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

        public void setFrom(String from) {
            this.from = from;
        }

        public String getFrom() {
            return from;
        }

        public void setGasUsed(String gasUsed) {
            this.gasUsed = gasUsed;
        }

        public String getGasUsed() {
            return gasUsed;
        }

        public void setLogs(List<Logs> logs) {
            this.logs = logs;
        }

        public List<Logs> getLogs() {
            return logs;
        }

        public void setLogsBloom(String logsBloom) {
            this.logsBloom = logsBloom;
        }

        public String getLogsBloom() {
            return logsBloom;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getTo() {
            return to;
        }

        public void setTransactionHash(String transactionHash) {
            this.transactionHash = transactionHash;
        }

        public String getTransactionHash() {
            return transactionHash;
        }

        public void setTransactionIndex(String transactionIndex) {
            this.transactionIndex = transactionIndex;
        }

        public String getTransactionIndex() {
            return transactionIndex;
        }

    }
}