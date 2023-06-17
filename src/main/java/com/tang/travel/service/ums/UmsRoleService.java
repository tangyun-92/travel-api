package com.tang.travel.service.ums;

import com.tang.travel.model.req.ums.UmsRoleListReq;
import com.tang.travel.model.req.ums.UmsRoleSaveReq;
import com.tang.travel.util.PageBean;

public interface UmsRoleService {
    PageBean getRoleList(UmsRoleListReq req);

    void saveRole(UmsRoleSaveReq req);

    void delete(Long[] ids);
}
