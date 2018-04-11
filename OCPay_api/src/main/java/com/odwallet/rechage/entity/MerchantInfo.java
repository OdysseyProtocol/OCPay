package com.odwallet.rechage.entity;

import java.util.Date;

public class MerchantInfo {
    private Integer id;

    private String merchantName;

    private String apikey;

    private String security;

    private Date updatedAt;

    private Integer updatedBy;

    private Date createdAt;

    private Integer createdBy;

    private String rechargeSuccessUrl;

    private String transferCallBackUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getRechargeSuccessUrl() {
        return rechargeSuccessUrl;
    }

    public void setRechargeSuccessUrl(String rechargeSuccessUrl) {
        this.rechargeSuccessUrl = rechargeSuccessUrl;
    }

    public String getTransferCallBackUrl() {
        return transferCallBackUrl;
    }

    public void setTransferCallBackUrl(String transferCallBackUrl) {
        this.transferCallBackUrl = transferCallBackUrl;
    }
}