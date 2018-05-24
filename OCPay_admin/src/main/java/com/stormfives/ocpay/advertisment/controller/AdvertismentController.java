package com.stormfives.ocpay.advertisment.controller;

import com.stormfives.ocpay.account.controller.req.AdminReq;
import com.stormfives.ocpay.advertisment.controller.req.AdvertismentReq;
import com.stormfives.ocpay.advertisment.service.AdvertismentService;
import com.stormfives.ocpay.common.exception.InvalidArgumentException;
import com.stormfives.ocpay.common.response.ResponseValue;
import com.stormfives.ocpay.token.vo.TokenVo;
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
public class AdvertismentController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AdvertismentService advertismentService;

    /**
     * 新增广告
     *
     * @return
     */
    @PostMapping("v1/add-advertisment")
    public ResponseValue addAdvertisment(@RequestBody AdvertismentReq advertismentReq) throws InvalidArgumentException {
        if (advertismentReq.getHomePageId() == null ||
                advertismentReq.getDirectType() == null ||
                advertismentReq.getShowSort() == null) {
            throw new InvalidArgumentException("param is error!");
        }
        Integer adminId = (Integer) request.getAttribute("adminId");

        return advertismentService.addAdvertisment(adminId, advertismentReq);
    }

    /**
     * 修改广告
     *
     * @return
     */
    @PostMapping("v1/edit-advertisment")
    public ResponseValue editAdvertisment(@RequestBody AdvertismentReq advertismentReq) throws InvalidArgumentException {
        if (advertismentReq.getHomePageId() == null ||
                advertismentReq.getDirectType() == null ||
                advertismentReq.getShowSort() == null ||
                advertismentReq.getId() == null) {
            throw new InvalidArgumentException("param is error!");
        }
        Integer adminId = (Integer) request.getAttribute("adminId");

        return advertismentService.editAdvertisment(adminId, advertismentReq);
    }

    /**
     * 删除广告
     *
     * @return
     */
    @PostMapping("v1/delete-advertisment")
    public ResponseValue deleteAdvertisment(@RequestBody AdvertismentReq advertismentReq) throws InvalidArgumentException {
        if (advertismentReq.getId() == null) {
            throw new InvalidArgumentException("param is error!");
        }
        return advertismentService.deleteAdvertisment(advertismentReq);
    }

    /**
     * 查询广告
     *
     * @return
     */
    @PostMapping("v1/get-advertisment")
    public ResponseValue getAdvertisment(@RequestBody AdvertismentReq advertismentReq) throws InvalidArgumentException {
        if (advertismentReq.getHomePageId()==null){
            throw new InvalidArgumentException("param is error!");
        }
        return advertismentService.getAdvertisment(advertismentReq);
    }


}
