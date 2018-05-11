package com.ocpay.wallet.bean;

import java.io.Serializable;

/**
 * Created by y on 2018/5/10.
 */

public class SettingsBean implements Serializable{

    TYPE type;
    int icRes;
    String title;
    int messageCount;

    public enum TYPE {
        NOTIFICATION, CONTACT, SYSTEM, USER, HELP, ABOUT_US
    }


    public SettingsBean(TYPE type, int icRes, String title) {
        this.type = type;
        this.icRes = icRes;
        this.title = title;
    }


    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public int getIcRes() {
        return icRes;
    }

    public void setIcRes(int icRes) {
        this.icRes = icRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
