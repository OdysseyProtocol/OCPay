package com.stormfives.ocpay.account.controller;

import com.stormfives.ocpay.account.controller.req.AdminReq;
import com.stormfives.ocpay.account.service.AdminService;
import com.stormfives.ocpay.common.exception.InvalidArgumentException;
import com.stormfives.ocpay.common.response.FailResponse;
import com.stormfives.ocpay.common.response.ResponseValue;
import com.stormfives.ocpay.common.response.SuccessResponse;
import com.stormfives.ocpay.common.util.MessageSourceUtil;
import com.stormfives.ocpay.token.vo.TokenVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: lyc
 * Date: 2018/3/14
 * Time: 下午2:51
 */
@RestController
@RequestMapping("/api/ocpay/")
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private MessageSourceUtil messageSourceUtil;

    @Autowired
    private AdminService adminService;


    /**
     * 登录
     *
     * @return
     */
    @PostMapping("v1/login")
    public TokenVo login(@RequestBody AdminReq adminReq, HttpServletRequest request) throws InvalidArgumentException {

        return adminService.login(adminReq.getName(), adminReq.getPassword());
    }


    /**
     * 刷新token
     */

    /**
     * 新增admin-wallet 后台管理账户
     *
     * @param adminReq
     * @param request
     * @return
     */
    @PostMapping("v1/add-admin")
    public ResponseValue addAdmin(@RequestBody AdminReq adminReq, HttpServletRequest request) throws InvalidArgumentException {

        Integer adminId = (Integer) request.getAttribute("adminId");

        boolean success = false;
        try {
            success = adminService.addAdmin(adminReq.getName(), adminReq.getPassword(), adminReq.getPhone(), adminReq.getRealName(), adminId);
        } catch (InvalidArgumentException e) {
            logger.error("新增用户逻辑异常!", e);
            return new FailResponse(e.getMessage());
        } catch (Exception e) {
            logger.error("新增用户程序异常!", e);
        }

        if (success)
            return new SuccessResponse("message", messageSourceUtil.getMessage("success"));

        return new FailResponse("新增账户失败");
    }


    /**
     * 修改密码
     *
     * @param adminReq
     * @param request
     * @return
     */
    @PostMapping("v1/admin-pwd")
    public ResponseValue resetPassword(@RequestBody AdminReq adminReq, HttpServletRequest request) {

        Integer adminId = (Integer) request.getAttribute("adminId");

        boolean success = false;
        try {
            success = adminService.resetPassword(adminId, adminReq.getPassword(), adminReq.getName());

        } catch (InvalidArgumentException e) {
            logger.error("修改密码逻辑异常!", e);
            return new FailResponse(e.getMessage());

        } catch (Exception e) {
            logger.error("修改密码程序异常!", e);
        }

        if (success)
            return new SuccessResponse("message", messageSourceUtil.getMessage("success"));

        return new FailResponse("修改密码失败!");
    }


}
