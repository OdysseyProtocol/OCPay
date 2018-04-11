package com.odwallet.rechage.dao;

import com.odwallet.rechage.entity.WalletGroup;
import com.odwallet.rechage.entity.WalletGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WalletGroupMapper {
    int countByExample(WalletGroupExample example);

    int deleteByExample(WalletGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WalletGroup record);

    int insertSelective(WalletGroup record);

    List<WalletGroup> selectByExample(WalletGroupExample example);

    WalletGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WalletGroup record, @Param("example") WalletGroupExample example);

    int updateByExample(@Param("record") WalletGroup record, @Param("example") WalletGroupExample example);

    int updateByPrimaryKeySelective(WalletGroup record);

    int updateByPrimaryKey(WalletGroup record);
}