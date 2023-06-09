package com.tang.travel.service.scenery;

import com.tang.travel.model.req.scenery.SceneryCategoryQueryReq;
import com.tang.travel.model.resp.scenery.SceneryCategoryListForCustomerResp;
import com.tang.travel.util.PageBean;

import java.util.List;

public interface SceneryCategoryService {
    PageBean list(SceneryCategoryQueryReq sceneryCategoryQueryReq);

    List<SceneryCategoryListForCustomerResp> listForCustomer(Integer parentId);
}
