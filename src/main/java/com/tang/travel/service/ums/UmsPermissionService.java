package com.tang.travel.service.ums;

import com.tang.travel.model.resp.ums.UmsPermissionAllListResp;

import java.util.List;

public interface UmsPermissionService {

    List<UmsPermissionAllListResp> getAllList(Integer parentId);

}
