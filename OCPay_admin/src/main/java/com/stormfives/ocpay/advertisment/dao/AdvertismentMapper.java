package com.stormfives.ocpay.advertisment.dao;

import com.stormfives.ocpay.advertisment.dao.entity.Advertisment;
import com.stormfives.ocpay.advertisment.dao.entity.AdvertismentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdvertismentMapper {
    int countByExample(AdvertismentExample example);

    int deleteByExample(AdvertismentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Advertisment record);

    int insertSelective(Advertisment record);

    List<Advertisment> selectByExampleWithBLOBs(AdvertismentExample example);

    List<Advertisment> selectByExample(AdvertismentExample example);

    Advertisment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Advertisment record, @Param("example") AdvertismentExample example);

    int updateByExampleWithBLOBs(@Param("record") Advertisment record, @Param("example") AdvertismentExample example);

    int updateByExample(@Param("record") Advertisment record, @Param("example") AdvertismentExample example);

    int updateByPrimaryKeySelective(Advertisment record);

    int updateByPrimaryKeyWithBLOBs(Advertisment record);

    int updateByPrimaryKey(Advertisment record);
}