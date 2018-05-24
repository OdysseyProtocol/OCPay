package com.stormfives.ocpay.advertisment.service;

import com.github.pagehelper.PageHelper;
import com.stormfives.ocpay.advertisment.controller.req.HomePageReq;
import com.stormfives.ocpay.advertisment.controller.resp.HomePageAdvertismentResp;
import com.stormfives.ocpay.advertisment.controller.resp.HomePageVo;
import com.stormfives.ocpay.advertisment.dao.AdvertismentMapper;
import com.stormfives.ocpay.advertisment.dao.HomePageContentMapper;
import com.stormfives.ocpay.advertisment.dao.entity.Advertisment;
import com.stormfives.ocpay.advertisment.dao.entity.AdvertismentExample;
import com.stormfives.ocpay.advertisment.dao.entity.HomePageContent;
import com.stormfives.ocpay.advertisment.dao.entity.HomePageContentExample;
import com.stormfives.ocpay.common.response.FailResponse;
import com.stormfives.ocpay.common.response.ResponseValue;
import com.stormfives.ocpay.common.response.SuccessResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liuhuan on 2018/5/22.
 */
@Service
public class HomePageService {


    @Autowired
    private HomePageContentMapper homePageContentMapper;

    @Autowired
    private AdvertismentMapper advertismentMapper;


    public ResponseValue addHomePage(Integer adminId, HomePageReq homePageReq) {
        HomePageContent pageContent = new HomePageContent();

        BeanUtils.copyProperties(homePageReq, pageContent);

        pageContent.setCreateBy(adminId);

        pageContent.setCreateTime(new Date());

        int i = homePageContentMapper.insertSelective(pageContent);

        if (i>0){
            return new SuccessResponse("Successful!");
        }

        return new FailResponse("add error!");
    }

    public ResponseValue editHomePage(Integer adminId, HomePageReq homePageReq) {

        HomePageContent pageContent = new HomePageContent();

        BeanUtils.copyProperties(homePageReq, pageContent);

        pageContent.setUpdateBy(adminId);

        pageContent.setUpdateTime(new Date());

        int i = homePageContentMapper.updateByPrimaryKeySelective(pageContent);

        if (i>0){
            return new SuccessResponse("Successful!");
        }

        return new FailResponse("update error");

    }


    public ResponseValue deleteHomePage(Integer id) {
        int i = homePageContentMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            return new SuccessResponse(true);
        }
        return new FailResponse("delete error");
    }

    public ResponseValue getHomePage(HomePageReq homePageReq) {

        PageHelper.startPage(homePageReq.getPageNum(),homePageReq.getPageSize());

        HomePageContentExample pageContentExample = new HomePageContentExample();

        pageContentExample.setOrderByClause("sort asc");

        List<HomePageContent> homePageContents = homePageContentMapper.selectByExample(pageContentExample);

        return new SuccessResponse(homePageContents);


    }

    public ResponseValue getHomePageAndAdvertisment() {
        HomePageContentExample contentExample = new HomePageContentExample();
        contentExample.setOrderByClause(" sort asc");
        List<HomePageContent> homePageContents = homePageContentMapper.selectByExample(contentExample);
        List<HomePageVo> homePageVos = new ArrayList<>();
        if (homePageContents!=null&&homePageContents.size()>0){
            homePageContents.forEach(r->{
                HomePageVo homePageVo = new HomePageVo();
                BeanUtils.copyProperties(r,homePageVo);
                List<Advertisment> advertisment = getAdvertisment(r.getId());
                homePageVo.setAdvertisments(advertisment);
                homePageVos.add(homePageVo);
            });
        }
        HomePageAdvertismentResp pageAdvertismentResp = new HomePageAdvertismentResp();
        pageAdvertismentResp.setHomePageVos(homePageVos);
        return new SuccessResponse(pageAdvertismentResp);
    }

    private List<Advertisment> getAdvertisment(Integer id) {
        AdvertismentExample advertismentExample = new AdvertismentExample();
        advertismentExample.setOrderByClause(" show_sort asc");
        advertismentExample.createCriteria().andHomePageIdEqualTo(id);
        return advertismentMapper.selectByExample(advertismentExample);
    }
}
