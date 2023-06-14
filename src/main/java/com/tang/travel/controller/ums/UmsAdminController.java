package com.tang.travel.controller.ums;

import com.tang.travel.common.ApiRestResponse;
import com.tang.travel.exception.BusinessExceptionEnum;
import com.tang.travel.model.req.ums.UmsAdminLoginReq;
import com.tang.travel.model.req.ums.UmsAdminRegisterReq;
import com.tang.travel.service.ums.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "user-management-ums")
@RequestMapping("/admin")
public class UmsAdminController {

    @Resource
    UmsAdminService umsAdminService;
    @Value("Bearer")
    private String tokenHead;

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

    @ApiOperation("登录")
    @PostMapping("/login")
    public ApiRestResponse login(@Valid @RequestBody UmsAdminLoginReq req) {
        String token = umsAdminService.login(req);
        if (token == null) {
            return ApiRestResponse.error(BusinessExceptionEnum.WRONG_PASSWORD);
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return ApiRestResponse.success(tokenMap);
    }
}
