package com.tang.travel.service.oms;

import com.tang.travel.model.req.oms.OmsOrderListReq;
import com.tang.travel.util.PageBean;

public interface OmsOrderService {
    PageBean getOrderByPage(OmsOrderListReq req);
}
