package com.tang.travel.model.dao;

import com.tang.travel.mbg.model.UmsAdmin;
import com.tang.travel.model.req.ums.UmsAdminListReq;
import com.tang.travel.model.resp.ums.UmsAdminListResp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UmsAdminMapperDao {

    UmsAdmin selectByName(String username);

    List<UmsAdminListResp> selectList(@Param("query") UmsAdminListReq req);
}
