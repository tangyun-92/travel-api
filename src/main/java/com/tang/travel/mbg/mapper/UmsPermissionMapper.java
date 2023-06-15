package com.tang.travel.mbg.mapper;

import com.tang.travel.mbg.model.UmsPermission;

public interface UmsPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsPermission record);

    int insertSelective(UmsPermission record);

    UmsPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsPermission record);

    int updateByPrimaryKey(UmsPermission record);
}