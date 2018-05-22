package com.ocpay.wallet.utils.web3j.response;

/**
 * Created by y on 2018/5/18.
 */

public class RateResponse {
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

    public   class Data {

        private String base;
        private long timestamp;
        private Rates rates;

        public void setBase(String base) {
            this.base = base;
        }

        public String getBase() {
            return base;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setRates(Rates rates) {
            this.rates = rates;
        }

        public Rates getRates() {
            return rates;
        }

    }

    public  class Rates {
        public double BITCNY;
        public double USDT;
        public double USD;
        public double CNY;


    }

}
