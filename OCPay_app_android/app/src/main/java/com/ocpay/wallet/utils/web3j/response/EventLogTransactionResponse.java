/**
 * Copyright 2018 bejson.com
 */
package com.ocpay.wallet.utils.web3j.response;

import java.util.List;

/**
 * Auto-generated: 2018-05-03 10:54:35
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class EventLogTransactionResponse extends BaseResponse {

    private String status;
    private String message;
    private List<EventTransaction> result;

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

    public void setResult(List<EventTransaction> result) {
        this.result = result;
    }

    public List<EventTransaction> getResult() {
        return result;
    }



}