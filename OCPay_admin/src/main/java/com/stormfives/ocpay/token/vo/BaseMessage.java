package com.stormfives.ocpay.token.vo;

/**
 * Created by zxb on 2016/10/13.
 */
public class BaseMessage {

    private String message;

    public BaseMessage(){

    }

    public BaseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
