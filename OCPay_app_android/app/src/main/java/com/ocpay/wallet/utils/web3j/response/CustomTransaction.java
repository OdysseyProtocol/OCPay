package com.ocpay.wallet.utils.web3j.response;

import com.ocpay.wallet.OCPWallet;
import com.ocpay.wallet.utils.TokenUtils;
import com.ocpay.wallet.utils.eth.OCPWalletUtils;
import com.snow.commonlibrary.utils.StringUtil;

import java.io.Serializable;
import java.math.BigDecimal;

import static com.ocpay.wallet.Constans.ETH.ETH_SCALE;
import static com.ocpay.wallet.Constans.ETH.ZERO_18;

public class CustomTransaction extends BaseTransaction implements Serializable {

    private String blockNumber;
    private String timeStamp;
    private String hash;
    private String nonce;
    private String blockHash;
    private String transactionIndex;
    private String from;
    private String to;
    private String value;
    private String gas;
    private String gasPrice;
    private String isError;
    private String txreceipt_status;
    private String input;
    private String contractAddress;
    private String cumulativeGasUsed;
    private String gasUsed;
    private String confirmations;

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

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getNonce() {
        return nonce;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setTransactionIndex(String transactionIndex) {
        this.transactionIndex = transactionIndex;
    }

    public String getTransactionIndex() {
        return transactionIndex;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getGas() {
        return gas;
    }

    public void setGasPrice(String gasPrice) {
        this.gasPrice = gasPrice;
    }

    public String getGasPrice() {
        return gasPrice;
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

    public void setInput(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
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

    public String getTransactionAmount() {
        if (StringUtil.isEmpty(value)) return "0 ETH";
        //ETH
        BigDecimal amount = new BigDecimal(value);
        if (amount.compareTo(new BigDecimal("0")) > 0) {
            BigDecimal divide = amount.divide(new BigDecimal(ZERO_18), ETH_SCALE, BigDecimal.ROUND_UP);
            return divide + " ETH";
        }

        //token
        if (input != null && input.length() == 138) {
            BigDecimal stAmount = OCPWalletUtils.getSTAmount(input);
            String tokenName = TokenUtils.getTokenNameByAddress(to);
            return stAmount + " " + tokenName;
        }
        return "";
    }

    public boolean isSend() {
        return OCPWallet.getCurrentWallet().getWalletAddress().equalsIgnoreCase(to);
    }


    public boolean isSuccess() {
        return "0".equals(isError) && "1".equals(txreceipt_status);
    }

    public BigDecimal getTransactionFee() {
        return OCPWalletUtils.getTransactionFee(new BigDecimal(getGasPrice()), new BigDecimal(getGasUsed()));
    }

    public String getTransferTo() {
        return isEthTransaction() ? to : OCPWalletUtils.getTransactionTo(input);

    }

    public boolean isEthTransaction() {
        if (StringUtil.isEmpty(value)) return false;
        //ETH
        BigDecimal amount = new BigDecimal(value);
        return amount.compareTo(new BigDecimal("0")) > 0 && input != null && input.length() ==2;
    }
}

/**
 * {
 * "status": "1",
 * "message": "OK",
 * "result": [{
 * "blockNumber": "3256600",
 * "timeStamp": "1526614537",
 * "hash": "0x8005d4c8d8fcf49488ed8e5b51d7d7715d5046fcd8dd7f49c891be11423fbe9e",
 * "nonce": "27",
 * "blockHash": "0xc9219d7b194aecbbdabe2a9e3cb30a3ab1f9b91487d3d81e33c95a5214954aa6",
 * "transactionIndex": "22",
 * "from": "0x7e8247c7d145debe8a8c2d2a2ab450992aa884c9",
 * "to": "0xd0b2215d39d6e4f47c4f76e85531296e404c6089",
 * "value": "1000000000000000000",
 * "gas": "21000",
 * "gasPrice": "1000000000",
 * "isError": "0",
 * "txreceipt_status": "1",
 * "input": "0x",
 * "contractAddress": "",
 * "cumulativeGasUsed": "4577153",
 * "gasUsed": "21000",
 * "confirmations": "33631"
 * }, {
 * "blockNumber": "3256600",
 * "timeStamp": "1526614537",
 * "hash": "0x13a18f3849a369704a8a84464c5fb0cecac591c83364ed0960045be7a4d6f126",
 * "nonce": "26",
 * "blockHash": "0xc9219d7b194aecbbdabe2a9e3cb30a3ab1f9b91487d3d81e33c95a5214954aa6",
 * "transactionIndex": "4",
 * "from": "0x7e8247c7d145debe8a8c2d2a2ab450992aa884c9",
 * "to": "0xd0b2215d39d6e4f47c4f76e85531296e404c6089",
 * "value": "1000000000000000000",
 * "gas": "41000",
 * "gasPrice": "4000000000",
 * "isError": "0",
 * "txreceipt_status": "1",
 * "input": "0x",
 * "contractAddress": "",
 * "cumulativeGasUsed": "573064",
 * "gasUsed": "21000",
 * "confirmations": "33631"
 * }, {
 * "blockNumber": "3252577",
 * "timeStamp": "1526562392",
 * "hash": "0x365f896f01d2daf86e2c8040527fa39a00f01d47da99eece2c40c5384964a8bb",
 * "nonce": "25",
 * "blockHash": "0x08091dfbd4f7168837e7e8cd5259bc08535d872643e1fd3e9b575b1c04ba9c1c",
 * "transactionIndex": "17",
 * "from": "0x7e8247c7d145debe8a8c2d2a2ab450992aa884c9",
 * "to": "0xd1bcbe82f40a9d7fbcbd28cca6043d72d66d8e9d",
 * "value": "0",
 * "gas": "210000",
 * "gasPrice": "4000000000",
 * "isError": "0",
 * "txreceipt_status": "1",
 * "input": "0xa9059cbb0000000000000000000000007e8247c7d145debe8a8c2d2a2ab450992aa884c9000000000000000000000000000000000000000000000000016345785d8a0000",
 * "contractAddress": "",
 * "cumulativeGasUsed": "4280609",
 * "gasUsed": "59569",
 * "confirmations": "37654"
 * }, {
 * "blockNumber": "3252517",
 * "timeStamp": "1526561599",
 * "hash": "0x61cef91e5b4b155289db3cc8f4923935c1c63e7a8342bdb3ed80ffde96d18c6a",
 * "nonce": "24",
 * "blockHash": "0xbb5ca23e74c1b7bcf1aa39a5698ded9e827bf28aaa0dd992068cf3e301997972",
 * "transactionIndex": "11",
 * "from": "0x7e8247c7d145debe8a8c2d2a2ab450992aa884c9",
 * "to": "0xd1bcbe82f40a9d7fbcbd28cca6043d72d66d8e9d",
 * "value": "0",
 * "gas": "41000",
 * "gasPrice": "4000000000",
 * "isError": "1",
 * "txreceipt_status": "0",
 * "input": "0xa9059cbb0000000000000000000000007e8247c7d145debe8a8c2d2a2ab450992aa884c9000000000000000000000000000000000000000000000000016345785d8a0000",
 * "contractAddress": "",
 * "cumulativeGasUsed": "1587016",
 * "gasUsed": "41000",
 * "confirmations": "37714"
 * }]
 * }
 */
