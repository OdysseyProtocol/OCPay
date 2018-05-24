package com.stormfives.ocpay.log.dao;

import com.stormfives.ocpay.log.dao.entity.LogOperateAdmin;
import com.stormfives.ocpay.log.dao.entity.LogOperateAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogOperateAdminMapper {
    int countByExample(LogOperateAdminExample example);

    int deleteByExample(LogOperateAdminExample example);

    int deleteByPrimaryKey(Integer logid);

    int insert(LogOperateAdmin record);

    int insertSelective(LogOperateAdmin record);

    List<LogOperateAdmin> selectByExample(LogOperateAdminExample example);

    LogOperateAdmin selectByPrimaryKey(Integer logid);

    int updateByExampleSelective(@Param("record") LogOperateAdmin record, @Param("example") LogOperateAdminExample example);

    int updateByExample(@Param("record") LogOperateAdmin record, @Param("example") LogOperateAdminExample example);

    int updateByPrimaryKeySelective(LogOperateAdmin record);

    int updateByPrimaryKey(LogOperateAdmin record);
}