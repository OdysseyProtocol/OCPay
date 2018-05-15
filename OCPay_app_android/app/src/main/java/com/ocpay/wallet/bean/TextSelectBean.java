package com.ocpay.wallet.bean;

import java.io.Serializable;

/**
 * Created by y on 2018/5/10.
 */

public class TextSelectBean implements Serializable {
    public enum CURRENCY {
        CNY, USD
    }

    public enum LANGUAGE {

    }

    String text;
    boolean isSelected;
    CURRENCY currency;
    LANGUAGE language;

    public TextSelectBean(String text, boolean isSelected, CURRENCY currency) {
        this.text = text;
        this.isSelected = isSelected;
        this.currency = currency;
    }


    public TextSelectBean(String text, boolean isSelected) {
        this.text = text;
        this.isSelected = isSelected;
    }

    public TextSelectBean(String text, boolean isSelected, LANGUAGE language) {
        this.text = text;
        this.isSelected = isSelected;
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public CURRENCY getCurrency() {
        return currency;
    }

    public void setCurrency(CURRENCY currency) {
        this.currency = currency;
    }

    public LANGUAGE getLanguage() {
        return language;
    }

    public void setLanguage(LANGUAGE language) {
        this.language = language;
    }
}
