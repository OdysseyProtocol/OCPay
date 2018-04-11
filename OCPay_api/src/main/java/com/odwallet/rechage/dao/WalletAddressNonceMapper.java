package com.odwallet.rechage.dao;

import com.odwallet.rechage.entity.WalletAddressNonce;
import com.odwallet.rechage.entity.WalletAddressNonceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WalletAddressNonceMapper {
    int countByExample(WalletAddressNonceExample example);

    int deleteByExample(WalletAddressNonceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WalletAddressNonce record);

    int insertSelective(WalletAddressNonce record);

    List<WalletAddressNonce> selectByExample(WalletAddressNonceExample example);

    WalletAddressNonce selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WalletAddressNonce record, @Param("example") WalletAddressNonceExample example);

    int updateByExample(@Param("record") WalletAddressNonce record, @Param("example") WalletAddressNonceExample example);

    int updateByPrimaryKeySelective(WalletAddressNonce record);

    int updateByPrimaryKey(WalletAddressNonce record);
}