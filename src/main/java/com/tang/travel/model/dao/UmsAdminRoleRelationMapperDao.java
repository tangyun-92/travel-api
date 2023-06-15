package com.tang.travel.model.dao;

import com.tang.travel.mbg.model.UmsAdminRoleRelation;

public interface UmsAdminRoleRelationMapperDao {

    UmsAdminRoleRelation selectRoleListByAdminId(Long adminId);

}