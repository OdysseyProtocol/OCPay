package com.odwallet.rechage.controller.req;

/**
 * Created by liuhuan on 2018/3/6.
 */
public class CreateWalletReq {
    private Integer merchantId;

    private String in;
    private String seed;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }
}
