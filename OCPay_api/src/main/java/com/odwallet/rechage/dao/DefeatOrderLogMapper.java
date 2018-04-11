package com.odwallet.rechage.dao;

import com.odwallet.rechage.entity.DefeatOrderLog;
import com.odwallet.rechage.entity.DefeatOrderLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DefeatOrderLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DefeatOrderLog record);

    int insertSelective(DefeatOrderLog record);

    List<DefeatOrderLog> selectByExample(DefeatOrderLogExample example);

    DefeatOrderLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DefeatOrderLog record, @Param("example") DefeatOrderLogExample example);

    int updateByExample(@Param("record") DefeatOrderLog record, @Param("example") DefeatOrderLogExample example);

    int updateByPrimaryKeySelective(DefeatOrderLog record);

    int updateByPrimaryKey(DefeatOrderLog record);
}