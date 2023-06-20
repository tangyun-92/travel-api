package com.tang.travel.model.dao;

import com.tang.travel.mbg.model.UmsRolePermissionRelation;

import java.util.List;

public interface UmsRolePermissionRelationMapperDao {
    List<UmsRolePermissionRelation> selectPermissionListByRoleList(Integer roleId);

    int deleteRolePermissionByRoleId(Long roleId);
}