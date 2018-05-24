package com.stormfives.ocpay.advertisment.controller.resp;

import java.util.List;

/**
 * Created by liuhuan on 2018/5/23.
 */
public class HomePageAdvertismentResp {
    List<HomePageVo> homePageVos;

    public List<HomePageVo> getHomePageVos() {
        return homePageVos;
    }

    public void setHomePageVos(List<HomePageVo> homePageVos) {
        this.homePageVos = homePageVos;
    }
}
