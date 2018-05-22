package com.ocpay.wallet.bean.home;


/**
 * Created by y on 2017/11/17.
 */

public class HomeBean extends BastBean {


    public GeneralizeBean generalizeBean;
    public MerchantBean merchantBean;
    public BannerBean bannerBean;

    private int type;

    public String jumpUrl;

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }


    public GeneralizeBean getGeneralizeBean() {
        return generalizeBean;
    }

    public void setGeneralizeBean(GeneralizeBean generalizeBean) {
        this.generalizeBean = generalizeBean;
    }


    public BannerBean getBannerBean() {
        if (bannerBean == null) bannerBean = new BannerBean();
        return bannerBean;
    }

    public void setBannerBean(BannerBean bannerBean) {
        this.bannerBean = bannerBean;
    }


    public MerchantBean getMerchantBean() {
        return merchantBean;
    }

    public void setMerchantBean(MerchantBean merchantBean) {
        this.merchantBean = merchantBean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;

    }


}
