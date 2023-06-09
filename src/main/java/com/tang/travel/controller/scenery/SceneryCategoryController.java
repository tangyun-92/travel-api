package com.tang.travel.controller.scenery;

import com.tang.travel.common.ApiRestResponse;
import com.tang.travel.model.req.scenery.SceneryCategoryQueryReq;
import com.tang.travel.model.resp.scenery.SceneryCategoryListForCustomerResp;
import com.tang.travel.service.scenery.SceneryCategoryService;
import com.tang.travel.util.PageBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

}
