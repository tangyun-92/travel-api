package com.tang.travel.model.dao;

import com.tang.travel.mbg.model.UmsAdminRoleRelation;
import org.apache.ibatis.annotations.Param;

public interface UmsAdminRoleRelationMapperDao {

    UmsAdminRoleRelation selectRoleListByAdminId(Long adminId);

    int deleteAdminRoleRelationByAdminId(Long adminId);

}