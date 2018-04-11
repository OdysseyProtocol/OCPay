package com.odwallet.rechage.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserCoinLog {
    private Integer id;

    private Integer merchantId;

    private Integer userid;

    private Integer changeType;

    private BigDecimal changeNum;

    private Integer coinId;

    private String coinName;

    private Date createTime;

    private String orderTxHash;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public BigDecimal getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(BigDecimal changeNum) {
        this.changeNum = changeNum;
    }

    public Integer getCoinId() {
        return coinId;
    }

    public void setCoinId(Integer coinId) {
        this.coinId = coinId;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrderTxHash() {
        return orderTxHash;
    }

    public void setOrderTxHash(String orderTxHash) {
        this.orderTxHash = orderTxHash;
    }
}