package com.odwallet.rechage.entity;

import java.util.Date;

public class ScheduleBlockNum {
    private Integer id;

    private Long startBlockNum;

    private Long endBlockNum;

    private Integer coinId;

    private Date lastScanTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getStartBlockNum() {
        return startBlockNum;
    }

    public void setStartBlockNum(Long startBlockNum) {
        this.startBlockNum = startBlockNum;
    }

    public Long getEndBlockNum() {
        return endBlockNum;
    }

    public void setEndBlockNum(Long endBlockNum) {
        this.endBlockNum = endBlockNum;
    }

    public Integer getCoinId() {
        return coinId;
    }

    public void setCoinId(Integer coinId) {
        this.coinId = coinId;
    }

    public Date getLastScanTime() {
        return lastScanTime;
    }

    public void setLastScanTime(Date lastScanTime) {
        this.lastScanTime = lastScanTime;
    }
}