package com.odwallet.rechage.dao;

import com.odwallet.rechage.entity.PlatformTransfer;
import com.odwallet.rechage.entity.PlatformTransferExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlatformTransferMapper {
    int countByExample(PlatformTransferExample example);

    int deleteByExample(PlatformTransferExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlatformTransfer record);

    int insertSelective(PlatformTransfer record);

    List<PlatformTransfer> selectByExample(PlatformTransferExample example);

    PlatformTransfer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PlatformTransfer record, @Param("example") PlatformTransferExample example);

    int updateByExample(@Param("record") PlatformTransfer record, @Param("example") PlatformTransferExample example);

    int updateByPrimaryKeySelective(PlatformTransfer record);

    int updateByPrimaryKey(PlatformTransfer record);
}