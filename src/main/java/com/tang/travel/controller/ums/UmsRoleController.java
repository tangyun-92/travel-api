package com.tang.travel.controller.ums;

import com.tang.travel.common.ApiRestResponse;
import com.tang.travel.mbg.model.UmsRolePermissionRelation;
import com.tang.travel.model.req.ums.UmsRoleListReq;
import com.tang.travel.model.req.ums.UmsRoleSaveReq;
import com.tang.travel.service.ums.UmsRoleService;
import com.tang.travel.util.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "role-management-admin")
@RequestMapping("/admin/role")
public class UmsRoleController {

    @Resource
    UmsRoleService umsRoleService;

    @ApiOperation("后台-角色列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ums:role:read')")
    public ApiRestResponse getRoleList(UmsRoleListReq req) {
        PageBean list = umsRoleService.getRoleList(req);
        return ApiRestResponse.success(list);
    }

    @ApiOperation("后台-新增/更新角色")
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ums:role:save')")
    public ApiRestResponse saveRole(@Valid @RequestBody UmsRoleSaveReq req) {
        umsRoleService.saveRole(req);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台-删除角色")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('ums:role:delete')")
    public ApiRestResponse deleteRole(@RequestParam Long[] ids) {
        umsRoleService.delete(ids);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台-根据角色id，获取该角色拥有的所有权限列表")
    @GetMapping("/currentPermissionList")
    @PreAuthorize("hasAuthority('ums:role:assignAuth')")
    public ApiRestResponse getCurrentPermissionListByRoleId(@RequestParam Integer roleId) {
        List<UmsRolePermissionRelation> permissionList = umsRoleService.getCurrentPermissionListByRoleId(roleId);
        return ApiRestResponse.success(permissionList);
    }

    @ApiOperation("后台-分配角色权限")
    @PostMapping("/assignAuth")
    @PreAuthorize("hasAuthority('ums:role:assignAuth')")
    public ApiRestResponse assignRoleAuth(@RequestParam Long roleId, @RequestParam List<Long> permissionIds) {
        umsRoleService.assignAuth(roleId, permissionIds);
        return ApiRestResponse.success();
    }

}
