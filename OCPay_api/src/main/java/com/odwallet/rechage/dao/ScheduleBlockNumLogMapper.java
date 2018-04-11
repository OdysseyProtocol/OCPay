package com.odwallet.rechage.dao;

import com.odwallet.rechage.entity.ScheduleBlockNumLog;
import com.odwallet.rechage.entity.ScheduleBlockNumLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScheduleBlockNumLogMapper {
    int countByExample(ScheduleBlockNumLogExample example);

    int deleteByExample(ScheduleBlockNumLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScheduleBlockNumLog record);

    int insertSelective(ScheduleBlockNumLog record);

    List<ScheduleBlockNumLog> selectByExample(ScheduleBlockNumLogExample example);

    ScheduleBlockNumLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScheduleBlockNumLog record, @Param("example") ScheduleBlockNumLogExample example);

    int updateByExample(@Param("record") ScheduleBlockNumLog record, @Param("example") ScheduleBlockNumLogExample example);

    int updateByPrimaryKeySelective(ScheduleBlockNumLog record);

    int updateByPrimaryKey(ScheduleBlockNumLog record);
}