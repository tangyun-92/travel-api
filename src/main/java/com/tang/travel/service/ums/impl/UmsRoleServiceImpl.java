package com.tang.travel.service.ums.impl;

import com.github.pagehelper.PageHelper;
import com.tang.travel.exception.BusinessException;
import com.tang.travel.exception.BusinessExceptionEnum;
import com.tang.travel.mbg.mapper.UmsRoleMapper;
import com.tang.travel.mbg.model.UmsRole;
import com.tang.travel.model.dao.UmsRoleMapperDao;
import com.tang.travel.model.req.ums.UmsRoleListReq;
import com.tang.travel.model.req.ums.UmsRoleSaveReq;
import com.tang.travel.service.ums.UmsRoleService;
import com.tang.travel.util.CopyUtil;
import com.tang.travel.util.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UmsRoleServiceImpl implements UmsRoleService {

    @Resource
    UmsRoleMapper umsRoleMapper;
    @Resource
    UmsRoleMapperDao umsRoleMapperDao;

    /**
     * 后台-角色列表
     * @param req
     * @return
     */
    @Override
    public PageBean getRoleList(UmsRoleListReq req) {
        PageHelper.startPage(req.getCurrent(), req.getPageSize());

        List<UmsRole> roleList = umsRoleMapperDao.selectList(req);
        return new PageBean(roleList);
    }

    /**
     * 后台-新增/更新角色
     * @param req
     */
    @Override
    public void saveRole(UmsRoleSaveReq req) {
        UmsRole umsRole = CopyUtil.copy(req, UmsRole.class);
        UmsRole umsRoleDB = umsRoleMapperDao.selectByName(req.getName());

        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            if (umsRoleDB != null) {
                throw new BusinessException(BusinessExceptionEnum.NAME_EXIST);
            }
            umsRole.setCreateTime(new Date());
            umsRoleMapper.insertSelective(umsRole);
        } else {
            // 更新
            if (umsRoleDB != null && !umsRoleDB.getId().equals(req.getId())) {
                throw new BusinessException(BusinessExceptionEnum.NAME_EXIST);
            }
            umsRoleMapper.updateByPrimaryKeySelective(umsRole);
        }
    }

    /**
     * 后台-删除角色
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
        umsRoleMapperDao.batchDelete(ids);
    }

    public void assignAuth(Long roleId) {

    }

}
