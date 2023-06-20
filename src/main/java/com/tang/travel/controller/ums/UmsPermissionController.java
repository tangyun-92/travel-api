package com.tang.travel.controller.ums;

import com.tang.travel.common.ApiRestResponse;
import com.tang.travel.model.resp.ums.UmsPermissionAllListResp;
import com.tang.travel.service.ums.UmsPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "permission-management-admin")
@RequestMapping("/admin/permission")
public class UmsPermissionController {

    @Resource
    UmsPermissionService umsPermissionService;

    @ApiOperation("后台-获取所有权限列表")
    @GetMapping("/allList")
    @PreAuthorize("hasAuthority('ums:role:assignAuth')")
    public ApiRestResponse getAllPermissionList() {
        List<UmsPermissionAllListResp> permissionList = umsPermissionService.getAllList(0);
        return ApiRestResponse.success(permissionList);
    }

}
