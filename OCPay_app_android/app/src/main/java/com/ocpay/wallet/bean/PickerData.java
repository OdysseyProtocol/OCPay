package com.ocpay.wallet.bean;

/**
 * Created by y on 2017/12/1.
 */

public class PickerData {
    String id;
    String value;

    //这个用来显示在PickerView上面的字符串,PickerView会通过反射获取getPickerViewText方法显示出来。
    public String getPickerViewText() {
        //这里还可以判断文字超长截断再提供显示
        return value;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PickerData(String id, String value) {
        this.id = id;
        this.value = value;
    }
}
