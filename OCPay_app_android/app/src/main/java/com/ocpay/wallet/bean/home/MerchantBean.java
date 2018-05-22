/**
 * Copyright 2017 bejson.com
 */
package com.ocpay.wallet.bean.home;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Auto-generated: 2017-11-25 19:12:5
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class MerchantBean implements Serializable {

    private int currentIndex;
    private int totalCount;
    private int totalPage;
    private List<Merchant> pageItems;
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

    public void setPageItems(List<Merchant> pageItems) {
        this.pageItems = pageItems;
    }

    public List<Merchant> getPageItems() {
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