package com.tang.travel.model.dao;

import com.tang.travel.mbg.model.UmsRole;
import com.tang.travel.model.req.ums.UmsRoleListReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsRoleMapperDao {

    List<UmsRole> selectList(@Param("query")UmsRoleListReq req);

    UmsRole selectByName(String name);

    long batchDelete(@Param("ids") Long[] ids);

}