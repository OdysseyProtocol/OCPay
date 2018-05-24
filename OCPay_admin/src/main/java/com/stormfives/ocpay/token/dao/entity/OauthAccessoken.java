package com.stormfives.ocpay.token.dao.entity;

import java.util.Date;

public class OauthAccessoken {
    private Integer id;

    private Integer resourceOwnerId;

    private String accessToken;

    private String refreshToken;

    private Integer expiresIn;

    private Date revokedAt;

    private Date createdAt;

    private String scope;

    private Integer type;

    private Integer count;

    private Integer merchantAppId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResourceOwnerId() {
        return resourceOwnerId;
    }

    public void setResourceOwnerId(Integer resourceOwnerId) {
        this.resourceOwnerId = resourceOwnerId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Date getRevokedAt() {
        return revokedAt;
    }

    public void setRevokedAt(Date revokedAt) {
        this.revokedAt = revokedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getMerchantAppId() {
        return merchantAppId;
    }

    public void setMerchantAppId(Integer merchantAppId) {
        this.merchantAppId = merchantAppId;
    }
}