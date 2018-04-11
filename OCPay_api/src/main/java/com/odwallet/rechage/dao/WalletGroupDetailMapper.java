package com.odwallet.rechage.dao;

import com.odwallet.rechage.entity.WalletGroupDetail;
import com.odwallet.rechage.entity.WalletGroupDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WalletGroupDetailMapper {
    int countByExample(WalletGroupDetailExample example);

    int deleteByExample(WalletGroupDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WalletGroupDetail record);

    int insertSelective(WalletGroupDetail record);

    List<WalletGroupDetail> selectByExample(WalletGroupDetailExample example);

    WalletGroupDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WalletGroupDetail record, @Param("example") WalletGroupDetailExample example);

    int updateByExample(@Param("record") WalletGroupDetail record, @Param("example") WalletGroupDetailExample example);

    int updateByPrimaryKeySelective(WalletGroupDetail record);

    int updateByPrimaryKey(WalletGroupDetail record);
}