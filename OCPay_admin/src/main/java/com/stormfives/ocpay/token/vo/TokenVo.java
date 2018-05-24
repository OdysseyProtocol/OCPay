package com.stormfives.ocpay.token.vo;

/**
 * Created by cc on 16/10/12.
 */
public class TokenVo {

    private Integer resourceOwnerId;
    private String accessToken;
    private String refreshToken;
    private String scope;
    private Integer expiresIn;


    public Integer getResourceOwnerId() {
        return this.resourceOwnerId;
    }

    public void setResourceOwnerId(Integer resourceOwnerId) {
        this.resourceOwnerId = resourceOwnerId;
    }

    public String getAccessToken() {
        return this.accessToken;
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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
}
