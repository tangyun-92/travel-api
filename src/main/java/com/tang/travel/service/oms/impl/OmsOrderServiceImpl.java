package com.tang.travel.service.oms.impl;

import com.github.pagehelper.PageHelper;
import com.tang.travel.mbg.model.OmsOrder;
import com.tang.travel.model.dao.OmsOrderMapperDao;
import com.tang.travel.model.req.oms.OmsOrderListReq;
import com.tang.travel.service.oms.OmsOrderService;
import com.tang.travel.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OmsOrderServiceImpl implements OmsOrderService {

    @Resource
    OmsOrderMapperDao omsOrderMapperDao;

    /**
     * 后台-订单列表
     * @param req
     * @return
     */
    @Override
    public PageBean getOrderByPage(OmsOrderListReq req) {
        PageHelper.startPage(req.getCurrent(), req.getPageSize());

        List<OmsOrder> orderList = omsOrderMapperDao.selectList(req);
        return new PageBean(orderList);
    }

}
