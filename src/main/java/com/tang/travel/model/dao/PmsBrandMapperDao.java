package com.tang.travel.model.dao;

import com.tang.travel.mbg.model.PmsBrand;
import com.tang.travel.model.req.pms.PmsBrandListReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsBrandMapperDao {

    List<PmsBrand> selectList(@Param("query") PmsBrandListReq req);

    PmsBrand selectByName(String name);

    long batchDelete(@Param("ids") Long[] ids);

}