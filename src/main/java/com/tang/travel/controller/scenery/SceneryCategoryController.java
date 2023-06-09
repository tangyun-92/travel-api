package com.tang.travel.controller.scenery;

import com.tang.travel.common.ApiRestResponse;
import com.tang.travel.model.req.scenery.SceneryCategoryQueryReq;
import com.tang.travel.model.req.scenery.SceneryCategorySaveReq;
import com.tang.travel.model.resp.scenery.SceneryCategoryListForCustomerResp;
import com.tang.travel.service.scenery.SceneryCategoryService;
import com.tang.travel.util.PageBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class SceneryCategoryController {

    @Resource
    private SceneryCategoryService sceneryCategoryService;

    @ApiOperation(value = "后台-景点分类列表")
    @GetMapping("/admin/scenery/category/list")
    public ApiRestResponse list(SceneryCategoryQueryReq sceneryCategoryQueryReq) {

        PageBean list = sceneryCategoryService.list(sceneryCategoryQueryReq);
        return ApiRestResponse.success(list);

    }

    @ApiOperation(value = "前台-景点分类列表")
    @GetMapping("/scenery/category/list")
    public ApiRestResponse listForCustomer() {
        List<SceneryCategoryListForCustomerResp> sceneryCategoryListForCustomerResp = sceneryCategoryService.listForCustomer(0);
        return ApiRestResponse.success(sceneryCategoryListForCustomerResp);
    }

    @ApiOperation(value = "后台-新增编辑景点分类")
    @PostMapping("/admin/scenery/category/save")
    public ApiRestResponse save(@Valid @RequestBody SceneryCategorySaveReq req) {
        sceneryCategoryService.save(req);
        return ApiRestResponse.success();
    }

    @ApiOperation(value = "后台-删除景点分类")
    @DeleteMapping("/admin/scenery/category/delete")
    public ApiRestResponse delete(Integer id) {
        sceneryCategoryService.delete(id);
        return ApiRestResponse.success();
    }
}
