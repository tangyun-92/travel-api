package com.tang.travel.model.dao;

import com.tang.travel.mbg.model.OmsOrder;
import com.tang.travel.model.req.oms.OmsOrderListReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OmsOrderMapperDao {

    List<OmsOrder> selectList(@Param("query") OmsOrderListReq req);

}
