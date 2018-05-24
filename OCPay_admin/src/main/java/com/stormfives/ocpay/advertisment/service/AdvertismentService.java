package com.stormfives.ocpay.advertisment.service;

import com.github.pagehelper.PageHelper;
import com.stormfives.ocpay.advertisment.controller.req.AdvertismentReq;
import com.stormfives.ocpay.advertisment.dao.AdvertismentMapper;
import com.stormfives.ocpay.advertisment.dao.HomePageContentMapper;
import com.stormfives.ocpay.advertisment.dao.entity.Advertisment;
import com.stormfives.ocpay.advertisment.dao.entity.AdvertismentExample;
import com.stormfives.ocpay.advertisment.dao.entity.HomePageContent;
import com.stormfives.ocpay.common.response.FailResponse;
import com.stormfives.ocpay.common.response.ResponseValue;
import com.stormfives.ocpay.common.response.SuccessResponse;
import com.stormfives.ocpay.token.domain.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by liuhuan on 2018/5/22.
 */
@Service
public class AdvertismentService {

    @Autowired
    private AdvertismentMapper advertismentMapper;

    @Autowired
    private HomePageContentMapper homePageContentMapper;

    public ResponseValue addAdvertisment(Integer adminId, AdvertismentReq advertismentReq) {

        HomePageContent pageContent = homePageContentMapper.selectByPrimaryKey(advertismentReq.getHomePageId());

        if (pageContent==null){
            return new FailResponse("homePageId is error!");
        }

        Advertisment advertisment = new Advertisment();

        BeanUtils.copyProperties(advertismentReq, advertisment);

        advertisment.setCreateBy(adminId);

        advertisment.setCreateTime(new Date());

        int i = advertismentMapper.insertSelective(advertisment);
        if (i > 0) {
            return new SuccessResponse("Successful!");
        }
        return new FailResponse("insert error!");

    }

    public ResponseValue editAdvertisment(Integer adminId, AdvertismentReq advertismentReq) {
        Advertisment advertisment = new Advertisment();

        BeanUtils.copyProperties(advertismentReq, advertisment);

        advertisment.setUpdateBy(adminId);

        advertisment.setUpdateTime(new Date());

        int i = advertismentMapper.updateByPrimaryKeySelective(advertisment);
        if (i > 0) {
            return new SuccessResponse("Successful!");
        }
        return new FailResponse("update error!");

    }

    public ResponseValue deleteAdvertisment(AdvertismentReq advertismentReq) {
        int i = advertismentMapper.deleteByPrimaryKey(advertismentReq.getId());
        if (i > 0) {
            return new SuccessResponse("Successful!");
        }
        return new FailResponse("delete error");

    }


    public ResponseValue getAdvertisment(AdvertismentReq advertismentReq) {

        PageHelper.startPage(advertismentReq.getPageNum(),advertismentReq.getPageSize());

        AdvertismentExample advertismentExample = new AdvertismentExample();

        advertismentExample.setOrderByClause("show_sort asc");

        advertismentExample.createCriteria().andHomePageIdEqualTo(advertismentReq.getHomePageId());

        List<Advertisment> advertisments = advertismentMapper.selectByExample(advertismentExample);

        return new SuccessResponse(advertisments);
    }
}
