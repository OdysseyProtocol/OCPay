package com.odwallet.rechage.entity;

import java.util.Date;

public class CoinInfo {
    private Integer id;

    private String coinName;

    private String contractAddress;

    private Date updatedAt;

    private Integer updatedBy;

    private Date createdAt;

    private Integer createdBy;

    private String fullName;

    private Integer status;

    private Integer coinLowerLimit;

    private Integer coinHigherLimit;

    private Integer dayCycleRound;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCoinLowerLimit() {
        return coinLowerLimit;
    }

    public void setCoinLowerLimit(Integer coinLowerLimit) {
        this.coinLowerLimit = coinLowerLimit;
    }

    public Integer getCoinHigherLimit() {
        return coinHigherLimit;
    }

    public void setCoinHigherLimit(Integer coinHigherLimit) {
        this.coinHigherLimit = coinHigherLimit;
    }

    public Integer getDayCycleRound() {
        return dayCycleRound;
    }

    public void setDayCycleRound(Integer dayCycleRound) {
        this.dayCycleRound = dayCycleRound;
    }
}