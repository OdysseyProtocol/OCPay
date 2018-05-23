package com.ocpay.wallet.utils.web3j.response;

import com.ocpay.wallet.MyApp;
import com.ocpay.wallet.OCPWallet;
import com.ocpay.wallet.utils.eth.OCPWalletUtils;
import com.ocpay.wallet.utils.web3j.utils.CommonUtils;
import com.snow.commonlibrary.utils.DateUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;

import static com.ocpay.wallet.utils.eth.OCPWalletUtils.getAmountByP1;
import static com.ocpay.wallet.utils.eth.OCPWalletUtils.getTransactionFee;
import static com.ocpay.wallet.utils.eth.OCPWalletUtils.subWalletAddress;
import static com.ocpay.wallet.utils.web3j.utils.CommonUtils.Hex2Decimal;

public class EventTransaction extends BaseTransaction implements Serializable {
    private String address;
    private List<String> topics;
    private String data;
    private String blockNumber;
    private String timeStamp;
    private String gasPrice;
    private String gasUsed;
    private String logIndex;
    private String transactionHash;
    private String transactionIndex;


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

    public BigInteger getBlockNumber() {
        return CommonUtils.Hex2Decimal(blockNumber);
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTimeStamp() {
        if (timeStamp.startsWith("0x")) {
            return Hex2Decimal(timeStamp).toString();
        }
        return timeStamp;
    }

    public void setGasPrice(String gasPrice) {
        this.gasPrice = gasPrice;
    }

    public String getGasPrice() {
        return gasPrice;
    }

    public void setGasUsed(String gasUsed) {
        this.gasUsed = gasUsed;
    }

    public String getGasUsed() {
        return gasUsed;
    }

    public void setLogIndex(String logIndex) {
        this.logIndex = logIndex;
    }

    public String getLogIndex() {
        return logIndex;
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


    public String getTimeFormat_dMy() {
        try {
            return DateUtils.TimeStamp2Date(Long.valueOf(getTimeStamp()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    public boolean isSend() {
        String topic1 = subWalletAddress(getTopics().get(1));
        return OCPWallet.getCurrentWallet().getWalletAddress().equalsIgnoreCase(topic1);
    }

    public String getTransferAmount() {
        return getAmountByP1(getData()).toString();
    }


    public String getTransferFrom() {
        return OCPWalletUtils.subWalletAddress(getTopics().get(1));

    }


    public String getRansferTo() {
        return OCPWalletUtils.subWalletAddress(getTopics().get(2));
    }


    public boolean getTransactionStatus() {
        if (MyApp.getEthBlockNumber().subtract(getBlockNumber()).longValue() > 12) {
            return true;
        } else {
            return false;
        }
    }


    public BigDecimal getFee() {
        BigDecimal price = new BigDecimal(Hex2Decimal(getGasPrice()).toString());
        BigDecimal used = new BigDecimal(Hex2Decimal(getGasUsed()).toString());
        return getTransactionFee(price, used);
    }



}