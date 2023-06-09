package com.tang.travel.model.dao;

import com.tang.travel.model.pojo.SceneryCategory;
import com.tang.travel.model.pojo.SceneryCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SceneryCategoryMapper {
    long countByExample(SceneryCategoryExample example);

    int deleteByExample(SceneryCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SceneryCategory record);

    int insertSelective(SceneryCategory record);

    List<SceneryCategory> selectByExample(SceneryCategoryExample example);

    SceneryCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SceneryCategory record, @Param("example") SceneryCategoryExample example);

    int updateByExample(@Param("record") SceneryCategory record, @Param("example") SceneryCategoryExample example);

    int updateByPrimaryKeySelective(SceneryCategory record);

    int updateByPrimaryKey(SceneryCategory record);
}