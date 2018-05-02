/**
 * Copyright 2017 bejson.com
 */
package com.ocpay.wallet.bean.home;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Auto-generated: 2017-11-25 19:12:33
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class BannerBean implements Serializable {

    private List<Banner> bannerList;

    public void setBannerList(List<Banner> bannerList) {
        this.bannerList = bannerList;
    }

    public List<Banner> getBannerList() {
        if (bannerList == null) bannerList = new ArrayList<>();
        return bannerList;
    }

}