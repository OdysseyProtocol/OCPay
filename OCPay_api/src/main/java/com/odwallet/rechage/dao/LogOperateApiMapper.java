package com.odwallet.rechage.dao;

import com.odwallet.rechage.entity.LogOperateApi;
import com.odwallet.rechage.entity.LogOperateApiExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogOperateApiMapper {
    int countByExample(LogOperateApiExample example);

    int deleteByExample(LogOperateApiExample example);

    int deleteByPrimaryKey(Integer logid);

    int insert(LogOperateApi record);

    int insertSelective(LogOperateApi record);

    List<LogOperateApi> selectByExample(LogOperateApiExample example);

    LogOperateApi selectByPrimaryKey(Integer logid);

    int updateByExampleSelective(@Param("record") LogOperateApi record, @Param("example") LogOperateApiExample example);

    int updateByExample(@Param("record") LogOperateApi record, @Param("example") LogOperateApiExample example);

    int updateByPrimaryKeySelective(LogOperateApi record);

    int updateByPrimaryKey(LogOperateApi record);
}