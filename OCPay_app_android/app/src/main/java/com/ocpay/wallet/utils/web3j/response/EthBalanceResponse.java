package com.ocpay.wallet.utils.web3j.response;

/**
 * Created by y on 2018/5/2.
 */

public class EthBalanceResponse {

    private String status;
    private String message;
    private String result;

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

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

}
