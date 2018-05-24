package com.stormfives.ocpay.advertisment.dao;

import com.stormfives.ocpay.advertisment.dao.entity.HomePageContent;
import com.stormfives.ocpay.advertisment.dao.entity.HomePageContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HomePageContentMapper {
    int countByExample(HomePageContentExample example);

    int deleteByExample(HomePageContentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HomePageContent record);

    int insertSelective(HomePageContent record);

    List<HomePageContent> selectByExample(HomePageContentExample example);

    HomePageContent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HomePageContent record, @Param("example") HomePageContentExample example);

    int updateByExample(@Param("record") HomePageContent record, @Param("example") HomePageContentExample example);

    int updateByPrimaryKeySelective(HomePageContent record);

    int updateByPrimaryKey(HomePageContent record);
}