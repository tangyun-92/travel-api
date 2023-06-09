package com.tang.travel.service.scenery.impl;

import com.github.pagehelper.PageHelper;
import com.tang.travel.model.dao.SceneryCategoryMapper;
import com.tang.travel.model.pojo.SceneryCategory;
import com.tang.travel.model.pojo.SceneryCategoryExample;
import com.tang.travel.model.req.scenery.SceneryCategoryQueryReq;
import com.tang.travel.model.resp.scenery.SceneryCategoryListForCustomerResp;
import com.tang.travel.service.scenery.SceneryCategoryService;
import com.tang.travel.util.PageBean;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SceneryCategoryServiceImpl implements SceneryCategoryService {

    @Resource
    private SceneryCategoryMapper sceneryCategoryMapper;

    /**
     * 后台-查询分类列表
     * @param req
     * @return
     */
    @Override
    public PageBean list(SceneryCategoryQueryReq req) {
        SceneryCategoryExample sceneryCategoryExample = new SceneryCategoryExample();
        SceneryCategoryExample.Criteria criteria = sceneryCategoryExample.createCriteria();

        // 查询所有未删除的数据
        criteria.andIsDelLike("1");
        // 分类名称模糊查询
        if (!ObjectUtils.isEmpty(req.getName())) {
            criteria.andNameLike('%' + req.getName() + '%');
        }

        PageHelper.startPage(req.getCurrent(), req.getPageSize());
        List<SceneryCategory> sceneryCategoryList = sceneryCategoryMapper.selectByExample(sceneryCategoryExample);
        return new PageBean<>(sceneryCategoryList);
    }

    /**
     * 前台-景点分类列表-树
     * @param parentId
     * @return
     */
    @Override
    public List<SceneryCategoryListForCustomerResp> listForCustomer(Integer parentId) {
        ArrayList<SceneryCategoryListForCustomerResp> sceneryCategoryListForCustomerResp = new ArrayList<>();
        recursivelyFindCategories(sceneryCategoryListForCustomerResp, parentId);
        return sceneryCategoryListForCustomerResp;
    }

    // 递归获取所有子类别，并组合成为一个“目录树”
    private void recursivelyFindCategories(List<SceneryCategoryListForCustomerResp> sceneryCategoryList, Integer parentId) {
        SceneryCategoryExample sceneryCategoryExample = new SceneryCategoryExample();
        SceneryCategoryExample.Criteria criteria = sceneryCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<SceneryCategory> categoryList = sceneryCategoryMapper.selectByExample(sceneryCategoryExample);

        if (!CollectionUtils.isEmpty(categoryList)) {
            for (int i = 0; i < categoryList.size(); i++) {
                SceneryCategory sceneryCategory =  categoryList.get(i);
                SceneryCategoryListForCustomerResp sceneryCategoryListForCustomerResp = new SceneryCategoryListForCustomerResp();
                BeanUtils.copyProperties(sceneryCategory, sceneryCategoryListForCustomerResp);
                sceneryCategoryList.add(sceneryCategoryListForCustomerResp);
                recursivelyFindCategories(sceneryCategoryListForCustomerResp.getChildren(), sceneryCategoryListForCustomerResp.getId());
            }
        }
    }
}
