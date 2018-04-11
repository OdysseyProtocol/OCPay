package com.odwallet.rechage.entity;

import java.util.Date;

public class LogOperateApi {
    private Integer logid;

    private Integer merchantId;

    private String actpath;

    private String param;

    private String seed;

    private Integer consumetime;

    private Date createtime;

    public Integer getLogid() {
        return logid;
    }

    public void setLogid(Integer logid) {
        this.logid = logid;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getActpath() {
        return actpath;
    }

    public void setActpath(String actpath) {
        this.actpath = actpath;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public Integer getConsumetime() {
        return consumetime;
    }

    public void setConsumetime(Integer consumetime) {
        this.consumetime = consumetime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}