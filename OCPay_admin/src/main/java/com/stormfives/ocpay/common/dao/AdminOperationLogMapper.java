package com.stormfives.ocpay.common.dao;

import com.stormfives.ocpay.common.dao.entity.AdminOperationLog;
import com.stormfives.ocpay.common.dao.entity.AdminOperationLogExample;
import com.stormfives.ocpay.common.dao.entity.AdminOperationLogWithBLOBs;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AdminOperationLogMapper {
    int countByExample(AdminOperationLogExample example);

    int deleteByExample(AdminOperationLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminOperationLogWithBLOBs record);

    int insertSelective(AdminOperationLogWithBLOBs record);

    List<AdminOperationLogWithBLOBs> selectByExampleWithBLOBs(AdminOperationLogExample example);

    List<AdminOperationLog> selectByExample(AdminOperationLogExample example);

    AdminOperationLogWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminOperationLogWithBLOBs record, @Param("example") AdminOperationLogExample example);

    int updateByExampleWithBLOBs(@Param("record") AdminOperationLogWithBLOBs record, @Param("example") AdminOperationLogExample example);

    int updateByExample(@Param("record") AdminOperationLog record, @Param("example") AdminOperationLogExample example);

    int updateByPrimaryKeySelective(AdminOperationLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AdminOperationLogWithBLOBs record);

    int updateByPrimaryKey(AdminOperationLog record);
}