/**
 * Copyright 2017 bejson.com
 */
package com.ocpay.wallet.bean.home;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GeneralizeBean implements Serializable {

    private int currentIndex;
    private int totalCount;
    private int totalPage;
    private List<Generalize> pageItems;
//    private PaginationCondition paginationCondition;

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setPageItems(List<Generalize> pageItems) {
        this.pageItems = pageItems;
    }

    public List<Generalize> getPageItems() {
        if (pageItems == null) pageItems = new ArrayList<>();
        return pageItems;
    }

//    public void setPaginationCondition(PaginationCondition paginationCondition) {
//        this.paginationCondition = paginationCondition;
//    }
//
//    public PaginationCondition getPaginationCondition() {
//        return paginationCondition;
//    }

}