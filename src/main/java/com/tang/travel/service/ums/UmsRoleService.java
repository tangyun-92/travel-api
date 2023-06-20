package com.tang.travel.service.ums;

import com.tang.travel.mbg.model.UmsRolePermissionRelation;
import com.tang.travel.model.req.ums.UmsRoleListReq;
import com.tang.travel.model.req.ums.UmsRoleSaveReq;
import com.tang.travel.util.PageBean;

import java.util.List;

public interface UmsRoleService {
    PageBean getRoleList(UmsRoleListReq req);

    void saveRole(UmsRoleSaveReq req);

    void delete(Long[] ids);

    List<UmsRolePermissionRelation> getCurrentPermissionListByRoleId(Integer roleId);

    void assignAuth(Long roleId, List<Long> permissionIds);
}
