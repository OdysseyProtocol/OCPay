package com.ocpay.wallet.utils.web3j.response;

import java.util.List;

/**
 * Created by y on 2018/5/23.
 */


public class EtherScanTxListResponse {
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

}