package com.ocpay.wallet.utils.web3j.response;

/**
 * Created by y on 2018/5/18.
 */

public class SymbolPairResponse {

    private int code;
    private String message;
    private Data data;

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

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public  class Data {

        public long timestamps;
        public double last;
        public double bid;
        public double ask;
        public double high;
        public double low;
        public double vol;
        public double base_volume;
        public double change_daily;
        public String market;
        public String symbol_name;
        public String symbol_pair;
        public int rating;
        public boolean has_kline;
        public double usd_rate;


    }
}
