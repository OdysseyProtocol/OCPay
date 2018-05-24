package com.stormfives.ocpay.advertisment.controller.req;

import jnr.ffi.annotations.In;

/**
 * Created by liuhuan on 2018/5/22.
 */
public class AdvertismentReq {

    private Integer id;
    private Integer homePageId;
    private Integer directType;
    private String directUrl;
    private Integer showSort;
    private String showImg;
    private String content;
    private String title;
    private Integer pageNum;
    private Integer pageSize;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHomePageId() {
        return homePageId;
    }

    public void setHomePageId(Integer homePageId) {
        this.homePageId = homePageId;
    }

    public Integer getDirectType() {
        return directType;
    }

    public void setDirectType(Integer directType) {
        this.directType = directType;
    }

    public String getDirectUrl() {
        return directUrl;
    }

    public void setDirectUrl(String directUrl) {
        this.directUrl = directUrl;
    }

    public Integer getShowSort() {
        return showSort;
    }

    public void setShowSort(Integer showSort) {
        this.showSort = showSort;
    }

    public String getShowImg() {
        return showImg;
    }

    public void setShowImg(String showImg) {
        this.showImg = showImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
