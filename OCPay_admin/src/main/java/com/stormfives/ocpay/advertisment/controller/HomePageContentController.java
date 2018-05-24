package com.stormfives.ocpay.advertisment.controller;

import com.stormfives.ocpay.advertisment.controller.req.HomePageReq;
import com.stormfives.ocpay.advertisment.service.HomePageService;
import com.stormfives.ocpay.common.exception.InvalidArgumentException;
import com.stormfives.ocpay.common.response.ResponseValue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liuhuan on 2018/5/22.
 */
@RestController
@RequestMapping("/api/ocpay/")
public class HomePageContentController {


    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HomePageService homePageService;


    /**
     * 新增类型
     *
     * @return
     */
    @PostMapping("v1/add-homePage")
    public ResponseValue addHomePage(@RequestBody HomePageReq homePageReq) throws InvalidArgumentException {
        if (StringUtils.isBlank(homePageReq.getContent())||homePageReq.getSort()==null||homePageReq.getSort()==null){
            throw new InvalidArgumentException("param is error!");
        }
        Integer adminId = (Integer) request.getAttribute("adminId");
        return homePageService.addHomePage(adminId,homePageReq);
    }


    /**
     * 编辑类型
     *
     * @return
     */
    @PostMapping("v1/edit-homePage")
    public ResponseValue editHomePage(@RequestBody HomePageReq homePageReq) throws InvalidArgumentException {
        if (StringUtils.isBlank(homePageReq.getContent())
                ||homePageReq.getSort()==null
                ||homePageReq.getSort()==null
                ||homePageReq.getId()==null){
            throw new InvalidArgumentException("param is error!");
        }
        Integer adminId = (Integer) request.getAttribute("adminId");
        return homePageService.editHomePage(adminId,homePageReq);
    }


    /**
     * 删除类型
     *
     * @return
     */
    @PostMapping("v1/delete-homePage")
    public ResponseValue deleteHomePage(@RequestBody HomePageReq homePageReq) throws InvalidArgumentException {
        if (homePageReq.getId()==null){
            throw new InvalidArgumentException("param is error!");
        }
        Integer adminId = (Integer) request.getAttribute("adminId");
        return homePageService.deleteHomePage(homePageReq.getId());
    }

    /**
     * 查看类型
     *
     * @return
     */
    @PostMapping("v1/get-homePage")
    public ResponseValue getHomePage(@RequestBody HomePageReq homePageReq) throws InvalidArgumentException {

        return homePageService.getHomePage(homePageReq);
    }


    /**
     * 查看类型
     *
     * @return
     */
    @PostMapping("v1/token/get-Advertisment")
    public ResponseValue getHomePageAndAdvertisment() throws InvalidArgumentException {
        return homePageService.getHomePageAndAdvertisment();
    }





}
