package com.ocpay.wallet.utils.web3j.response;

import java.util.List;

/**
 * Created by y on 2018/5/18.
 */

public class TokenPriceResponse {


    private int code;
    private String message;
    private List<TokenPriceDate> data;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setData(List<TokenPriceDate> data) {
        this.data = data;
    }

    public List<TokenPriceDate> getData() {
        return data;
    }


    public static class TokenPriceDate {

        public String name;
        public String symbol;
        public double price;
        public double high;
        public double low;
        public long timestamps;
        public double volume;
        public double change_hourly;
        public double change_daily;
        public double change_weekly;
        public double change_monthly;



    }
}
