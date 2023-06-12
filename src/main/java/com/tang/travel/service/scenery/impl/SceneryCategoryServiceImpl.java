package com.tang.travel.service.scenery.impl;

import com.github.pagehelper.PageHelper;
import com.tang.travel.exception.BusinessException;
import com.tang.travel.exception.BusinessExceptionEnum;
import com.tang.travel.model.dao.SceneryCategoryMapper;
import com.tang.travel.model.pojo.SceneryCategory;
import com.tang.travel.model.pojo.SceneryCategoryExample;
import com.tang.travel.model.req.scenery.SceneryCategoryQueryReq;
import com.tang.travel.model.req.scenery.SceneryCategorySaveReq;
import com.tang.travel.model.resp.scenery.SceneryCategoryListForCustomerResp;
import com.tang.travel.service.scenery.SceneryCategoryService;
import com.tang.travel.util.CopyUtil;
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
        criteria.andIsDelEqualTo("0");
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

    /**
     * 新增编辑景点分类
     * @param req
     */
    @Override
    public void save(SceneryCategorySaveReq req) {
        SceneryCategory sceneryCategory = CopyUtil.copy(req, SceneryCategory.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            SceneryCategory sceneryCategoryDB = selectByName(req.getName());
            if (ObjectUtils.isEmpty(sceneryCategoryDB)) {
                sceneryCategoryMapper.insertSelective(sceneryCategory);
            } else {
                // 分类名称已存在
                throw new BusinessException(BusinessExceptionEnum.NAME_EXIST);
            }
        } else {
            // 更新
            sceneryCategoryMapper.updateByPrimaryKeySelective(sceneryCategory);
        }
    }

    /**
     * 删除景点分类 - 逻辑删除
     * @param id
     */
    @Override
    public void delete(Integer id) {
        SceneryCategory sceneryCategoryDB = sceneryCategoryMapper.selectByPrimaryKey(id);
        System.out.println(sceneryCategoryDB);
        if (!ObjectUtils.isEmpty(sceneryCategoryDB)) {
            sceneryCategoryDB.setIsDel("1");
            sceneryCategoryMapper.updateByPrimaryKey(sceneryCategoryDB);
        }
    }

    /**
     * 根据分类名称查询分类详细信息
     * @param name
     * @return
     */
    public SceneryCategory selectByName(String name) {
        SceneryCategoryExample sceneryCategoryExample = new SceneryCategoryExample();
        SceneryCategoryExample.Criteria criteria = sceneryCategoryExample.createCriteria();
        criteria.andNameEqualTo(name);

        List<SceneryCategory> list = sceneryCategoryMapper.selectByExample(sceneryCategoryExample);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
