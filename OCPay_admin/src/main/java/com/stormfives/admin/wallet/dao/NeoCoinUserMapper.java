package com.stormfives.admin.wallet.dao;

import com.stormfives.admin.wallet.dao.entity.NeoCoinUser;
import com.stormfives.admin.wallet.dao.entity.NeoCoinUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NeoCoinUserMapper {
    int countByExample(NeoCoinUserExample example);

    int deleteByExample(NeoCoinUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(NeoCoinUser record);

    int insertSelective(NeoCoinUser record);

    List<NeoCoinUser> selectByExample(NeoCoinUserExample example);

    NeoCoinUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") NeoCoinUser record, @Param("example") NeoCoinUserExample example);

    int updateByExample(@Param("record") NeoCoinUser record, @Param("example") NeoCoinUserExample example);

    int updateByPrimaryKeySelective(NeoCoinUser record);

    int updateByPrimaryKey(NeoCoinUser record);
}