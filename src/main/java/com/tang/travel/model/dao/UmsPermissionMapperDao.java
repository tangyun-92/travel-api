package com.tang.travel.model.dao;

import com.tang.travel.mbg.model.UmsPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UmsPermissionMapperDao {
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}