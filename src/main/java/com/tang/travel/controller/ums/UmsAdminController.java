package com.tang.travel.controller.ums;

import com.tang.travel.common.ApiRestResponse;
import com.tang.travel.model.req.ums.UmsAdminRegisterReq;
import com.tang.travel.service.ums.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Api(tags = "user-management-ums")
@RequestMapping("/admin")
public class UmsAdminController {

    @Resource
    UmsAdminService umsAdminService;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public ApiRestResponse register(@Valid @RequestBody UmsAdminRegisterReq req) {
        umsAdminService.register(req);
        return ApiRestResponse.success();
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @GetMapping("/permissionList")
    public ApiRestResponse getPermissionList(@RequestParam Long adminId) {
        umsAdminService.getPermissionList(adminId);
        return ApiRestResponse.success();
    }
}
