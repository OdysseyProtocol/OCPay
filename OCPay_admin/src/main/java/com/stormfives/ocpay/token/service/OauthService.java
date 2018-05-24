package com.stormfives.ocpay.token.service;

import com.stormfives.ocpay.token.dao.TokenMapper;
import com.stormfives.ocpay.token.domain.Token;
import com.stormfives.ocpay.token.vo.TokenVo;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by cc on 16/10/12.
 */
@Service
public class OauthService {

    @Autowired
    TokenMapper tokenMapper;

    @Autowired
    RedisTemplate<String, Token> redisTemplate;

    /**
     * 验证
     *
     * @param accessToken
     * @return
     */
    public TokenVo getTokenByAccessToken(String accessToken) {

        Token token = redisTemplate.opsForValue().get(accessToken);
        if (token == null) {
            token = tokenMapper.selectByAccessToken(accessToken);
            if (token == null) return null;
            redisTemplate.opsForValue().set(token.getAccessToken(), token, 2, TimeUnit.HOURS);
        }

        TokenVo tokenVo = new TokenVo();
        BeanUtils.copyProperties(token, tokenVo);
        return tokenVo;
    }

    /**
     * 验证
     *
     * @param accessToken
     * @return
     */
    public Token getTokenByRefreshToken(String accessToken) {

        Token token = tokenMapper.selectByRefreshToken(accessToken);
        if (token == null) return null;
        return token;
    }


    public Token generateToken(int resourceOwnerId,String scope) {
        OAuthIssuerImpl authIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
        String accessToken = null;
        String refreshToken = null;
        try {
            accessToken = authIssuerImpl.accessToken();
            refreshToken = authIssuerImpl.refreshToken();
        } catch (Exception e) {

        }
        Token token = new Token();
        token.setResourceOwnerId(resourceOwnerId);
        token.setScope(scope);
        List<Token> tokenList = tokenMapper.selectLatestByOwnerId(token);
        //web端跟app端可以同时登录


        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setScope(scope);
        token.setExpiresIn(5184000);
        token.setCount(1);

        tokenMapper.deleteByOwnerId(token);
        tokenMapper.insert(token);
        for(Token tempToken : tokenList) {
            redisTemplate.delete(tempToken.getAccessToken());
        }
        redisTemplate.opsForValue().set(token.getAccessToken(), token, 2, TimeUnit.HOURS);
        return token;
    }

    public Token generateToken(int resourceOwnerId,String scope,Integer type) {
        OAuthIssuerImpl authIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
        String accessToken = null;
        String refreshToken = null;
        try {
            accessToken = authIssuerImpl.accessToken();
            refreshToken = authIssuerImpl.refreshToken();
        } catch (Exception e) {

        }
        Token token = new Token();
        token.setResourceOwnerId(resourceOwnerId);
        token.setScope(scope);
        List<Token> tokenList = tokenMapper.selectLatestByOwnerId(token);
        //web端跟app端可以同时登录

        if (tokenList!= null && tokenList.size()>0){

            if (type!=null)
                if (!type.equals(tokenList.get(0).getType())){
                    Integer count = tokenList.get(0).getCount();
                    if(count ==null)
                        count =1;

                    if (count<2){
                        tokenList.get(0).setCount(++count);
                        tokenMapper.updateByPrimaryKeySelective(tokenList.get(0));
                        return tokenList.get(0);
                    }
                }

        }

        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setScope(scope);
        token.setExpiresIn(5184000);
        token.setType(type);
        token.setCount(1);

        tokenMapper.deleteByOwnerId(token);
        tokenMapper.insert(token);
        for(Token tempToken : tokenList) {
            redisTemplate.delete(tempToken.getAccessToken());
        }
        redisTemplate.opsForValue().set(token.getAccessToken(), token, 2, TimeUnit.HOURS);
        return token;
    }

    public Boolean deleteAccessToken(Token originalToken) {
        List<Token> tokens = tokenMapper.selectLatestByOwnerId(originalToken);
        if (tokens != null){
            for (Token token : tokens){
                tokenMapper.deleteByOwnerId(token);
                redisTemplate.delete(token.getAccessToken());
            }
        }
        return true;
    }
}
