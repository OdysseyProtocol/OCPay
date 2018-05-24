package com.stormfives.ocpay.common;

/**
 * Created by zxb on 2016/10/14.
 */
public class BaseMessage {

    private String message;

    public BaseMessage(){

    }

    public BaseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
