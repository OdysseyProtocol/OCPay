package com.stormfives.ocpay.common;

/**
 * Created by tlw on 2017/5/10.
 */
public class SuccessBaseMessage extends  BaseMessage {
    private boolean success;
    public SuccessBaseMessage(String message){
        success = true;
        setMessage(message);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
