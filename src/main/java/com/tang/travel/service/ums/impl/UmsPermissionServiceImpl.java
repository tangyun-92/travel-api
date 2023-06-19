package com.tang.travel.service.ums.impl;

import com.tang.travel.mbg.model.UmsPermission;
import com.tang.travel.model.dao.UmsPermissionMapperDao;
import com.tang.travel.model.resp.ums.UmsPermissionAllListResp;
import com.tang.travel.service.ums.UmsPermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UmsPermissionServiceImpl implements UmsPermissionService {

    @Resource
    UmsPermissionMapperDao umsPermissionMapperDao;

    @Override
    public List<UmsPermissionAllListResp> getAllList(Integer parentId) {
        ArrayList<UmsPermissionAllListResp> categoryVOList = new ArrayList<>();
        recursivelyFindCategories(categoryVOList, parentId);
        return categoryVOList;
    }

    private void recursivelyFindCategories(List<UmsPermissionAllListResp> categoryVOList, Integer parentId) {
        // 递归获取所有子类别，并组合成为一个“目录树”
        List<UmsPermission> categoryList = umsPermissionMapperDao.selectPermissionsByParentId(parentId);
        if (!CollectionUtils.isEmpty(categoryList)) {
            for (int i = 0; i < categoryList.size(); i++) {
                UmsPermission category =  categoryList.get(i);
                UmsPermissionAllListResp categoryVO = new UmsPermissionAllListResp();
                BeanUtils.copyProperties(category, categoryVO);
                categoryVOList.add(categoryVO);
                recursivelyFindCategories(categoryVO.getChildren(), categoryVO.getId());
            }
        }
    }

}
