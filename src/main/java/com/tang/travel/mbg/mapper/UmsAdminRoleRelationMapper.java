package com.tang.travel.mbg.mapper;

import com.tang.travel.mbg.model.UmsAdminRoleRelation;

public interface UmsAdminRoleRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminRoleRelation record);

    int insertSelective(UmsAdminRoleRelation record);

    UmsAdminRoleRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsAdminRoleRelation record);

    int updateByPrimaryKey(UmsAdminRoleRelation record);
}