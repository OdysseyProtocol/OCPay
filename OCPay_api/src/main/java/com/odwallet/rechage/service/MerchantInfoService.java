package com.odwallet.rechage.service;

import com.odwallet.rechage.dao.MerchantInfoMapper;
import com.odwallet.rechage.entity.MerchantInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liuhuan on 2018/3/6.
 */
@Service
public class MerchantInfoService {

    @Autowired
    private MerchantInfoMapper merchantInfoMapper;

    public MerchantInfo getMerchantInfoById(Integer merchantInfoId){
        return merchantInfoMapper.selectByPrimaryKey(merchantInfoId);
    }

}
