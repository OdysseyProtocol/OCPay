package com.stormfives.ocpay.token.dao;

import com.stormfives.ocpay.token.domain.Token;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TokenMapper {

    /****************generator by plugin*****start**************/
    int insert(Token token);

    Token selectByAccessToken(String accessToken);

    Token selectByRefreshToken(String refreshToken);

    int deleteByOwnerId(Token token);

    List<Token> selectLatestByOwnerId(Token token);

    int updateByPrimaryKeySelective(Token token);


    /****************generator by plugin******end*************/

}