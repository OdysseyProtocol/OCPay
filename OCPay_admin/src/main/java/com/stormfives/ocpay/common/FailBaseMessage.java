package com.stormfives.ocpay.common;

/**
 * Created by tlw on 2017/5/10.
 */
public class FailBaseMessage extends  BaseMessage {
    private boolean success;
    public FailBaseMessage(String message){
        success = false;
        setMessage(message);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
