package com.tang.travel.service.pms.impl;

import com.github.pagehelper.PageHelper;
import com.tang.travel.exception.BusinessException;
import com.tang.travel.exception.BusinessExceptionEnum;
import com.tang.travel.mbg.mapper.PmsBrandMapper;
import com.tang.travel.mbg.model.PmsBrand;
import com.tang.travel.model.dao.PmsBrandMapperDao;
import com.tang.travel.model.req.pms.PmsBrandListReq;
import com.tang.travel.model.req.pms.PmsBrandSaveReq;
import com.tang.travel.service.pms.PmsBrandService;
import com.tang.travel.util.CopyUtil;
import com.tang.travel.util.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Resource
    PmsBrandMapperDao pmsBrandMapperDao;
    @Resource
    PmsBrandMapper pmsBrandMapper;

    /**
     * 后台-品牌列表
     * @param req
     * @return
     */
    @Override
    public PageBean getBrandList(PmsBrandListReq req) {
        PageHelper.startPage(req.getCurrent(), req.getPageSize());

        List<PmsBrand> brandList = pmsBrandMapperDao.selectList(req);
        return new PageBean(brandList);
    }

    /**
     * 后台-新增/更新品牌
     * @param req
     */
    @Override
    public void saveBrand(PmsBrandSaveReq req) {
        PmsBrand pmsBrand = CopyUtil.copy(req, PmsBrand.class);
        PmsBrand pmsBrandDB = pmsBrandMapperDao.selectByName(req.getName());

        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            if (pmsBrandDB != null) {
                throw new BusinessException(BusinessExceptionEnum.NAME_EXIST);
            }

            pmsBrandMapper.insertSelective(pmsBrand);
        } else {
            // 更新
            if (pmsBrandDB != null && !pmsBrandDB.getId().equals(req.getId())) {
                throw new BusinessException(BusinessExceptionEnum.NAME_EXIST);
            }
            pmsBrandMapper.updateByPrimaryKeySelective(pmsBrand);
        }
    }

    /**
     * 后台-删除品牌
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
        pmsBrandMapperDao.batchDelete(ids);
    }
}
