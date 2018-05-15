package com.ocpay.wallet.bean;

import com.ocpay.wallet.utils.web3j.response.EtherScanJsonrpcResponse;

import java.io.Serializable;

/**
 * Created by y on 2018/5/10.
 */

public class ZipSignBean implements Serializable {
    String password;
    EtherScanJsonrpcResponse etherScanJsonrpcResponse;


    public ZipSignBean(String s, EtherScanJsonrpcResponse etherScanJsonrpcResponse) {
        this.password = s;
        this.etherScanJsonrpcResponse = etherScanJsonrpcResponse;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EtherScanJsonrpcResponse getEtherScanJsonrpcResponse() {
        return etherScanJsonrpcResponse;
    }
}
