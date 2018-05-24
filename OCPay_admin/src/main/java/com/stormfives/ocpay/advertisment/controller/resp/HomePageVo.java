package com.stormfives.ocpay.advertisment.controller.resp;

import com.stormfives.ocpay.advertisment.dao.entity.Advertisment;
import com.stormfives.ocpay.advertisment.dao.entity.HomePageContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuhuan on 2018/5/23.
 */
public class HomePageVo extends HomePageContent{

    List<Advertisment> advertisments = new ArrayList<>();

    public List<Advertisment> getAdvertisments() {
        return advertisments;
    }

    public void setAdvertisments(List<Advertisment> advertisments) {
        this.advertisments = advertisments;
    }
}
