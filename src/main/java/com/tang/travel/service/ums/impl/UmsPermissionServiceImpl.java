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
        ArrayList<UmsPermissionAllListResp> permissionListResp = new ArrayList<>();
        recursivelyFindCategories(permissionListResp, parentId);
        return permissionListResp;
    }

    private void recursivelyFindCategories(List<UmsPermissionAllListResp> permissionListResp, Integer parentId) {
        // 递归获取所有子类别，并组合成为一个“目录树”
        List<UmsPermission> permissionList = umsPermissionMapperDao.selectPermissionsByParentId(parentId);
        if (!CollectionUtils.isEmpty(permissionList)) {
            for (int i = 0; i < permissionList.size(); i++) {
                UmsPermission permission =  permissionList.get(i);
                UmsPermissionAllListResp permissionResp = new UmsPermissionAllListResp();
                BeanUtils.copyProperties(permission, permissionResp);
                permissionListResp.add(permissionResp);
                recursivelyFindCategories(permissionResp.getChildren(), permissionResp.getId());
            }
        }
    }

}
