package com.tang.travel.service.ums;

import com.tang.travel.mbg.model.UmsAdmin;
import com.tang.travel.mbg.model.UmsAdminRoleRelation;
import com.tang.travel.mbg.model.UmsPermission;
import com.tang.travel.model.req.ums.UmsAdminListReq;
import com.tang.travel.model.req.ums.UmsAdminLoginReq;
import com.tang.travel.model.req.ums.UmsAdminRegisterReq;
import com.tang.travel.model.req.ums.UmsAdminSaveReq;
import com.tang.travel.util.PageBean;

import java.util.List;

public interface UmsAdminService {
    UmsAdmin getAdminByUsername(String username);

    void register(UmsAdminRegisterReq req);

    List<UmsPermission> getPermissionList(Long adminId);

    String login(UmsAdminLoginReq req);

    PageBean list(UmsAdminListReq req);

    void saveUser(UmsAdminSaveReq req);

    void deleteUser(Long[] ids);

    void assignRole(Long adminId, List<Long> roleIds);

    List<UmsAdminRoleRelation> getUserRoleListByAdminId(Long adminId);
}
