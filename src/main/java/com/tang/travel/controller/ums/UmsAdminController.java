package com.tang.travel.controller.ums;

import com.tang.travel.common.ApiRestResponse;
import com.tang.travel.exception.BusinessExceptionEnum;
import com.tang.travel.mbg.model.UmsAdmin;
import com.tang.travel.mbg.model.UmsAdminRoleRelation;
import com.tang.travel.mbg.model.UmsPermission;
import com.tang.travel.model.req.ums.UmsAdminListReq;
import com.tang.travel.model.req.ums.UmsAdminLoginReq;
import com.tang.travel.model.req.ums.UmsAdminRegisterReq;
import com.tang.travel.model.req.ums.UmsAdminSaveReq;
import com.tang.travel.service.ums.UmsAdminService;
import com.tang.travel.util.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "user-management-admin")
@RequestMapping("/admin")
public class UmsAdminController {

    @Resource
    UmsAdminService umsAdminService;
    @Value("${jwt.tokenHead}")
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

    @ApiOperation("获取用户详细信息与权限列表")
    @GetMapping(value = "/userInfo")
    public ApiRestResponse getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UmsAdmin umsAdmin = umsAdminService.getAdminByUsername(username);
        umsAdmin.setPassword(null);

        HashMap<String, Object> hashMap = new HashMap<>();
        List<UmsPermission> permissionList = umsAdminService.getPermissionList(umsAdmin.getId());
        hashMap.put("permissionList", permissionList);
        hashMap.put("info", umsAdmin);

        return ApiRestResponse.success(hashMap);
    }

    @ApiOperation("后台-用户列表")
    @GetMapping("/user/list")
    @PreAuthorize("hasAuthority('ums:admin:read')")
    public ApiRestResponse getUserList(UmsAdminListReq req) {
        PageBean list = umsAdminService.list(req);
        return ApiRestResponse.success(list);
    }

    @ApiOperation("后台-新增/更新用户")
    @PostMapping("/user/save")
    @PreAuthorize("hasAnyAuthority('ums:admin:save')")
    public ApiRestResponse saveUser(@Valid @RequestBody UmsAdminSaveReq req) {
        umsAdminService.saveUser(req);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台-删除用户")
    @DeleteMapping("/user/delete")
    @PreAuthorize("hasAuthority('ums:admin:delete')")
    public ApiRestResponse deleteUser(@RequestParam Long[] ids) {
        umsAdminService.deleteUser(ids);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台-分配角色")
    @PostMapping("/user/assignRole")
    @PreAuthorize("hasAuthority('ums:admin:assignRole')")
    public ApiRestResponse assignRole(@RequestParam Long adminId, @RequestParam List<Long> roleIds) {
        umsAdminService.assignRole(adminId, roleIds);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台-根据用户id获取角色id列表")
    @GetMapping("/user/getUserRoleListByAdminId")
    @PreAuthorize("hasAuthority('ums:admin:assignRole')")
    public ApiRestResponse getUserRoleListByAdminId(Long adminId) {
        List<UmsAdminRoleRelation> roleList = umsAdminService.getUserRoleListByAdminId(adminId);
        return ApiRestResponse.success(roleList);
    }
}
