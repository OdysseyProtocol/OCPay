/**
 * Copyright 2018 bejson.com
 */
package com.ocpay.wallet.utils.web3j.response;

import java.util.List;

/**
 *
 */
public class EthTransactionResponse extends BaseResponse {

    private String status;
    private String message;
    private List<EthTransaction> result;

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

    public void setResult(List<EthTransaction> result) {
        this.result = result;
    }

    public List<EthTransaction> getResult() {
        return result;
    }




}