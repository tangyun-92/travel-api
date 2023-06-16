package com.tang.travel.model.dao;

import com.tang.travel.mbg.model.UmsAdminRoleRelation;

import java.util.List;

public interface UmsAdminRoleRelationMapperDao {

    List<UmsAdminRoleRelation> selectRoleListByAdminId(Long adminId);

    int deleteAdminRoleRelationByAdminId(Long adminId);

}