package com.stormfives.admin.wallet.controller;

import com.stormfives.admin.account.controller.req.AdminReq;
import com.stormfives.admin.account.service.AdminService;
import com.stormfives.admin.common.exception.InvalidArgumentException;
import com.stormfives.admin.common.response.FailResponse;
import com.stormfives.admin.common.response.ResponseValue;
import com.stormfives.admin.common.response.SuccessResponse;
import com.stormfives.admin.common.util.MessageSourceUtil;
import com.stormfives.admin.token.domain.Page;
import com.stormfives.admin.token.vo.TokenVo;
import com.stormfives.admin.wallet.controller.req.UserReq;
import com.stormfives.admin.wallet.service.UserService;
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
@RequestMapping("/api/coin/")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "v1/coin-balance/users")
    public Page getUserCoinBalances(@ModelAttribute UserReq userReq) {


        return null;
    }



}
