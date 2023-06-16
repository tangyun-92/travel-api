package com.tang.travel.service.pms;

import com.tang.travel.model.req.pms.PmsBrandListReq;
import com.tang.travel.model.req.pms.PmsBrandSaveReq;
import com.tang.travel.util.PageBean;

public interface PmsBrandService {
    PageBean getBrandList(PmsBrandListReq req);

    void saveBrand(PmsBrandSaveReq req);

    void delete(Long[] ids);
}
