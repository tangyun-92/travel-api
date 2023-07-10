package com.tang.travel.controller.oms;

import com.tang.travel.common.ApiRestResponse;
import com.tang.travel.model.req.oms.OmsOrderListReq;
import com.tang.travel.service.oms.OmsOrderService;
import com.tang.travel.util.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "order-management-admin")
public class OmsOrderController {

    @Resource
    OmsOrderService omsOrderService;

    @ApiOperation("后台-订单列表")
    @GetMapping("/admin/order/list")
    @PreAuthorize("hasAuthority('oms:order:read')")
    public ApiRestResponse getOrderByPage(OmsOrderListReq req) {
        PageBean list = omsOrderService.getOrderByPage(req);
        return ApiRestResponse.success(list);
    }

}
