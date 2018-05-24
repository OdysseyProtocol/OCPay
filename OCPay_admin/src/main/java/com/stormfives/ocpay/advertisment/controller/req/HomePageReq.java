package com.stormfives.ocpay.advertisment.controller.req;

import jnr.ffi.annotations.In;

/**
 * Created by liuhuan on 2018/5/22.
 */
public class HomePageReq {

    private Integer id;

    private Integer type;

    private Integer sort;

    private String content;

    private Integer pageNum;

    private Integer pageSize;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
