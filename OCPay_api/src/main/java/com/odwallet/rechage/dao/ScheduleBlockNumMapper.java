package com.odwallet.rechage.dao;

import com.odwallet.rechage.entity.ScheduleBlockNum;
import com.odwallet.rechage.entity.ScheduleBlockNumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScheduleBlockNumMapper {
    int countByExample(ScheduleBlockNumExample example);

    int deleteByExample(ScheduleBlockNumExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScheduleBlockNum record);

    int insertSelective(ScheduleBlockNum record);

    List<ScheduleBlockNum> selectByExample(ScheduleBlockNumExample example);

    ScheduleBlockNum selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScheduleBlockNum record, @Param("example") ScheduleBlockNumExample example);

    int updateByExample(@Param("record") ScheduleBlockNum record, @Param("example") ScheduleBlockNumExample example);

    int updateByPrimaryKeySelective(ScheduleBlockNum record);

    int updateByPrimaryKey(ScheduleBlockNum record);
}