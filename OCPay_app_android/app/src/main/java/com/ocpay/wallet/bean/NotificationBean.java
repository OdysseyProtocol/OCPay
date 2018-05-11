package com.ocpay.wallet.bean;

import java.io.Serializable;

/**
 * Created by y on 2018/5/10.
 */

public class NotificationBean implements Serializable {

    String title;

    String content;

    String time;

    boolean read;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isRead() {
        return read;
    }

    public NotificationBean(String title, String content, String time, boolean read) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.read = read;
    }
}
