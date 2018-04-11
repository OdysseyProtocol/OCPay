package com.stormfives.admin.wallet.dao;

import com.stormfives.admin.wallet.dao.entity.UserNeoWallet;
import com.stormfives.admin.wallet.dao.entity.UserNeoWalletExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserNeoWalletMapper {
    int countByExample(UserNeoWalletExample example);

    int deleteByExample(UserNeoWalletExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(UserNeoWallet record);

    int insertSelective(UserNeoWallet record);

    List<UserNeoWallet> selectByExample(UserNeoWalletExample example);

    UserNeoWallet selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") UserNeoWallet record, @Param("example") UserNeoWalletExample example);

    int updateByExample(@Param("record") UserNeoWallet record, @Param("example") UserNeoWalletExample example);

    int updateByPrimaryKeySelective(UserNeoWallet record);

    int updateByPrimaryKey(UserNeoWallet record);
}