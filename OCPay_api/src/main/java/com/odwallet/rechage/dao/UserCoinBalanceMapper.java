package com.odwallet.rechage.dao;

import com.odwallet.rechage.entity.UserCoinBalance;
import com.odwallet.rechage.entity.UserCoinBalanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCoinBalanceMapper {
    int countByExample(UserCoinBalanceExample example);

    int deleteByExample(UserCoinBalanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserCoinBalance record);

    int insertSelective(UserCoinBalance record);

    List<UserCoinBalance> selectByExample(UserCoinBalanceExample example);

    UserCoinBalance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserCoinBalance record, @Param("example") UserCoinBalanceExample example);

    int updateByExample(@Param("record") UserCoinBalance record, @Param("example") UserCoinBalanceExample example);

    int updateByPrimaryKeySelective(UserCoinBalance record);

    int updateByPrimaryKey(UserCoinBalance record);

    UserCoinBalance selectByUserIdAndMerchantInfoId(@Param("userId")Integer userId,@Param("merchantInfoId") Integer merchantInfoId);

}