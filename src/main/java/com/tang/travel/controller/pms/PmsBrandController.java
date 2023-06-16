package com.tang.travel.controller.pms;

import com.tang.travel.common.ApiRestResponse;
import com.tang.travel.model.req.pms.PmsBrandListReq;
import com.tang.travel.model.req.pms.PmsBrandSaveReq;
import com.tang.travel.service.pms.PmsBrandService;
import com.tang.travel.util.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Api(tags = "brand-management-admin")
@RequestMapping("/admin/brand")
public class PmsBrandController {

    @Resource
    PmsBrandService pmsBrandService;

    @ApiOperation("后台-品牌列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public ApiRestResponse getUserList(PmsBrandListReq req) {
        PageBean list = pmsBrandService.getBrandList(req);
        return ApiRestResponse.success(list);
    }

    @ApiOperation("后台-新增/更新品牌")
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('pms:brand:save')")
    public ApiRestResponse saveUser(@Valid @RequestBody PmsBrandSaveReq req) {
        pmsBrandService.saveBrand(req);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台-删除品牌")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('pms:brand:delete')")
    public ApiRestResponse deleteBrand(@RequestParam Long[] ids) {
        pmsBrandService.delete(ids);
        return ApiRestResponse.success();
    }

}
