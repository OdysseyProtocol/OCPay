package com.stormfives.ocpay.log.dao.entity;

import java.util.Date;

public class LogOperateAdmin {
    private Integer logid;

    private Integer userid;

    private String actpath;

    private Integer consumetime;

    private Date createtime;

    private String param;

    private String ip;

    public Integer getLogid() {
        return logid;
    }

    public void setLogid(Integer logid) {
        this.logid = logid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getActpath() {
        return actpath;
    }

    public void setActpath(String actpath) {
        this.actpath = actpath;
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

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}