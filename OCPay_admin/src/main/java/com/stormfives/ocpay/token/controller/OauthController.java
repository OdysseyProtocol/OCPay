package com.stormfives.ocpay.token.controller;


import com.stormfives.ocpay.token.domain.Token;
import com.stormfives.ocpay.token.service.OauthService;
import com.stormfives.ocpay.token.vo.TokenVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cc on 16/10/12.
 */
@RestController
@RequestMapping("/api/coin/")
public class OauthController {

    @Autowired
    OauthService oauthService;

    @RequestMapping(value = "v1/token",method = RequestMethod.POST)
    public Token insert(@RequestBody Token tokenReq) {
        Token token = oauthService.generateToken(tokenReq.getResourceOwnerId(),tokenReq.getScope(),tokenReq.getType());
        return token;
    }

    @RequestMapping(value = "v1/access_token",method = RequestMethod.POST)
    public TokenVo getTokenByAccessToken(@RequestBody Token token) throws Exception {
        TokenVo tokenVo = oauthService.getTokenByAccessToken(token.getAccessToken());
        return tokenVo;
    }

    @RequestMapping(value = "v1/refresh_token",method = RequestMethod.POST)
    public TokenVo getTokenByRefreshToken(@RequestBody Token token) {

        Token tokenDAO = oauthService.getTokenByRefreshToken(token.getRefreshToken());
        if (tokenDAO == null) return null;
        tokenDAO = oauthService.generateToken(tokenDAO.getResourceOwnerId(),tokenDAO.getScope(),tokenDAO.getType());
        TokenVo tokenVo = new TokenVo();
        BeanUtils.copyProperties(tokenDAO,tokenVo);
        return tokenVo;
    }

    @RequestMapping(value ="v1/oauth/token",method = RequestMethod.DELETE)
    public Boolean deleteAccessToken(@RequestBody Token token) throws Exception{
        return oauthService.deleteAccessToken(token);
    }

}
