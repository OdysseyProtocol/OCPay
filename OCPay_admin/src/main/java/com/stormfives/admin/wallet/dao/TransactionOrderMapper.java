package com.stormfives.admin.wallet.dao;

import com.stormfives.admin.wallet.dao.entity.TransactionOrder;
import com.stormfives.admin.wallet.dao.entity.TransactionOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransactionOrderMapper {
    int countByExample(TransactionOrderExample example);

    int deleteByExample(TransactionOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TransactionOrder record);

    int insertSelective(TransactionOrder record);

    List<TransactionOrder> selectByExample(TransactionOrderExample example);

    TransactionOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TransactionOrder record, @Param("example") TransactionOrderExample example);

    int updateByExample(@Param("record") TransactionOrder record, @Param("example") TransactionOrderExample example);

    int updateByPrimaryKeySelective(TransactionOrder record);

    int updateByPrimaryKey(TransactionOrder record);
}