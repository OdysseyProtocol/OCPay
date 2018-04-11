package com.odwallet.rechage.dao;

import com.odwallet.rechage.entity.UserWalletInfo;
import com.odwallet.rechage.entity.UserWalletInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserWalletInfoMapper {
    int countByExample(UserWalletInfoExample example);

    int deleteByExample(UserWalletInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserWalletInfo record);

    int insertSelective(UserWalletInfo record);

    List<UserWalletInfo> selectByExample(UserWalletInfoExample example);

    UserWalletInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserWalletInfo record, @Param("example") UserWalletInfoExample example);

    int updateByExample(@Param("record") UserWalletInfo record, @Param("example") UserWalletInfoExample example);

    int updateByPrimaryKeySelective(UserWalletInfo record);

    int updateByPrimaryKey(UserWalletInfo record);

    UserWalletInfo selectByUserIdAndMerchantInfoId(@Param("userId")Integer userId,@Param("merchantInfoId") Integer merchantInfoId);

    UserWalletInfo selectWalletInfoByAddress(String address);
}