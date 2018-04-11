package com.stormfives.admin.wallet.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserNeoWallet {
    private Integer userid;

    private String neoWalletAddress;

    private BigDecimal balanceQlc;

    private BigDecimal balanceNeo;

    private BigDecimal balanceOnt;

    private Integer state;

    private String ethWalletPrivateKey;

    private String ethWalletAddress;

    private BigDecimal balanceEth;

    private Date bindWalletTime;

    private Date updateTime;

    private Date createTime;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getNeoWalletAddress() {
        return neoWalletAddress;
    }

    public void setNeoWalletAddress(String neoWalletAddress) {
        this.neoWalletAddress = neoWalletAddress;
    }

    public BigDecimal getBalanceQlc() {
        return balanceQlc;
    }

    public void setBalanceQlc(BigDecimal balanceQlc) {
        this.balanceQlc = balanceQlc;
    }

    public BigDecimal getBalanceNeo() {
        return balanceNeo;
    }

    public void setBalanceNeo(BigDecimal balanceNeo) {
        this.balanceNeo = balanceNeo;
    }

    public BigDecimal getBalanceOnt() {
        return balanceOnt;
    }

    public void setBalanceOnt(BigDecimal balanceOnt) {
        this.balanceOnt = balanceOnt;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getEthWalletPrivateKey() {
        return ethWalletPrivateKey;
    }

    public void setEthWalletPrivateKey(String ethWalletPrivateKey) {
        this.ethWalletPrivateKey = ethWalletPrivateKey;
    }

    public String getEthWalletAddress() {
        return ethWalletAddress;
    }

    public void setEthWalletAddress(String ethWalletAddress) {
        this.ethWalletAddress = ethWalletAddress;
    }

    public BigDecimal getBalanceEth() {
        return balanceEth;
    }

    public void setBalanceEth(BigDecimal balanceEth) {
        this.balanceEth = balanceEth;
    }

    public Date getBindWalletTime() {
        return bindWalletTime;
    }

    public void setBindWalletTime(Date bindWalletTime) {
        this.bindWalletTime = bindWalletTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}