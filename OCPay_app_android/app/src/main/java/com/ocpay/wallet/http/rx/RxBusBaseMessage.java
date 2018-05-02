package com.ocpay.wallet.http.rx;

/**
 * Created by y on 2018/4/17.
 */

public class RxBusBaseMessage {

    int code;
    Object object;


    public RxBusBaseMessage(int code, Object object) {
        this.code = code;
        this.object = object;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {

        this.object = object;
    }
}
