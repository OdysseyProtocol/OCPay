package com.odwallet.rechage.dao;

import com.odwallet.rechage.entity.UserCoinLog;
import com.odwallet.rechage.entity.UserCoinLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCoinLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCoinLog record);

    int insertSelective(UserCoinLog record);

    List<UserCoinLog> selectByExample(UserCoinLogExample example);

    UserCoinLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserCoinLog record, @Param("example") UserCoinLogExample example);

    int updateByExample(@Param("record") UserCoinLog record, @Param("example") UserCoinLogExample example);

    int updateByPrimaryKeySelective(UserCoinLog record);

    int updateByPrimaryKey(UserCoinLog record);
}