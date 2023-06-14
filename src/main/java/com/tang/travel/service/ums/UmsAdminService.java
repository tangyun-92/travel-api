package com.tang.travel.service.ums;

import com.tang.travel.mbg.model.UmsAdmin;
import com.tang.travel.mbg.model.UmsPermission;
import com.tang.travel.model.req.ums.UmsAdminLoginReq;
import com.tang.travel.model.req.ums.UmsAdminRegisterReq;

import java.util.List;

public interface UmsAdminService {
    UmsAdmin getAdminByUsername(String username);

    void register(UmsAdminRegisterReq req);

    List<UmsPermission> getPermissionList(Long adminId);

    String login(UmsAdminLoginReq req);
}
