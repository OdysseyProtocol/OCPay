/**
 * Copyright 2018 bejson.com
 */
package com.ocpay.wallet.utils.web3j.response;

import java.util.List;

/**
 * Auto-generated: 2018-03-10 17:50:34
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class BlockInfoResponse {

    private String status;
    private String message;
    private BlockInfo result;

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

    public void setResult(BlockInfo result) {
        this.result = result;
    }

    public BlockInfo getResult() {
        return result;
    }


    public static class BlockInfo {

        private String blockNumber;
        private String timeStamp;
        private String blockMiner;
        private String blockReward;
        private List<Uncles> uncles;
        private String uncleInclusionReward;

        public void setBlockNumber(String blockNumber) {
            this.blockNumber = blockNumber;
        }

        public String getBlockNumber() {
            return blockNumber;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setBlockMiner(String blockMiner) {
            this.blockMiner = blockMiner;
        }

        public String getBlockMiner() {
            return blockMiner;
        }

        public void setBlockReward(String blockReward) {
            this.blockReward = blockReward;
        }

        public String getBlockReward() {
            return blockReward;
        }

        public void setUncles(List<Uncles> uncles) {
            this.uncles = uncles;
        }

        public List<Uncles> getUncles() {
            return uncles;
        }

        public void setUncleInclusionReward(String uncleInclusionReward) {
            this.uncleInclusionReward = uncleInclusionReward;
        }

        public String getUncleInclusionReward() {
            return uncleInclusionReward;
        }

    }

    public static class Uncles {

        private String miner;
        private String unclePosition;
        private String blockreward;

        public void setMiner(String miner) {
            this.miner = miner;
        }

        public String getMiner() {
            return miner;
        }

        public void setUnclePosition(String unclePosition) {
            this.unclePosition = unclePosition;
        }

        public String getUnclePosition() {
            return unclePosition;
        }

        public void setBlockreward(String blockreward) {
            this.blockreward = blockreward;
        }

        public String getBlockreward() {
            return blockreward;
        }

    }
}