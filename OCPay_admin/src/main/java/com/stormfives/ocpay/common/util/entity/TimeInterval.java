package com.stormfives.ocpay.common.util.entity;

import java.util.Date;

/**
 * 时间区间
 * Created by tlw on 2017/6/5.
 */
public class TimeInterval {
    private Date startTime;
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
