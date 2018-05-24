package com.stormfives.ocpay.token.dao;

import com.stormfives.ocpay.token.dao.entity.OauthAccessoken;
import com.stormfives.ocpay.token.dao.entity.OauthAccessokenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OauthAccessokenMapper {
    int countByExample(OauthAccessokenExample example);

    int deleteByExample(OauthAccessokenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OauthAccessoken record);

    int insertSelective(OauthAccessoken record);

    List<OauthAccessoken> selectByExample(OauthAccessokenExample example);

    OauthAccessoken selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OauthAccessoken record, @Param("example") OauthAccessokenExample example);

    int updateByExample(@Param("record") OauthAccessoken record, @Param("example") OauthAccessokenExample example);

    int updateByPrimaryKeySelective(OauthAccessoken record);

    int updateByPrimaryKey(OauthAccessoken record);
}