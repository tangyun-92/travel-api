package com.tang.travel.service.ums.impl;

import com.github.pagehelper.PageHelper;
import com.tang.travel.exception.BusinessException;
import com.tang.travel.exception.BusinessExceptionEnum;
import com.tang.travel.mbg.mapper.UmsAdminLoginLogMapper;
import com.tang.travel.mbg.mapper.UmsAdminMapper;
import com.tang.travel.mbg.mapper.UmsAdminRoleRelationMapper;
import com.tang.travel.mbg.model.UmsAdmin;
import com.tang.travel.mbg.model.UmsAdminLoginLog;
import com.tang.travel.mbg.model.UmsAdminRoleRelation;
import com.tang.travel.mbg.model.UmsPermission;
import com.tang.travel.model.dao.UmsAdminMapperDao;
import com.tang.travel.model.dao.UmsAdminRoleRelationMapperDao;
import com.tang.travel.model.dao.UmsPermissionMapperDao;
import com.tang.travel.model.req.ums.UmsAdminListReq;
import com.tang.travel.model.req.ums.UmsAdminLoginReq;
import com.tang.travel.model.req.ums.UmsAdminRegisterReq;
import com.tang.travel.model.req.ums.UmsAdminSaveReq;
import com.tang.travel.model.resp.ums.UmsAdminListResp;
import com.tang.travel.service.ums.UmsAdminService;
import com.tang.travel.util.CopyUtil;
import com.tang.travel.util.JwtTokenUtil;
import com.tang.travel.util.PageBean;
import com.tang.travel.util.RequestUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Resource
    UmsAdminMapper umsAdminMapper;
    @Resource
    UmsAdminMapperDao umsAdminMapperDao;
    @Resource
    UmsPermissionMapperDao umsPermissionMapperDao;
    @Resource
    UserDetailsService userDetailsService;
    @Resource
    UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;
    @Resource
    UmsAdminRoleRelationMapperDao umsAdminRoleRelationMapperDao;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    UmsAdminLoginLogMapper umsAdminLoginLogMapper;

    /**
     * 后台-根据用户名查询指定用户详细信息
     *
     * @param username
     * @return
     */
    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdmin umsAdmin = umsAdminMapperDao.selectByName(username);
        if (umsAdmin != null) {
            return umsAdmin;
        }
        return null;
    }

    /**
     * 后台-注册
     *
     * @param req
     */
    @Override
    public void register(UmsAdminRegisterReq req) {
        UmsAdmin umsAdminDB = getAdminByUsername(req.getUsername());
        if (ObjectUtils.isEmpty(umsAdminDB)) {
            // 执行注册
            UmsAdmin umsAdmin = CopyUtil.copy(req, UmsAdmin.class);
            umsAdmin.setCreateTime(new Date());
            umsAdmin.setStatus(1);
            // 密码加密
            String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
            umsAdmin.setPassword(encodePassword);
            umsAdminMapper.insertSelective(umsAdmin);
        } else {
            throw new BusinessException(BusinessExceptionEnum.NAME_EXIST);
        }
    }

    /**
     * 后台-获取用户所有权限
     *
     * @param adminId
     * @return
     */
    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return umsPermissionMapperDao.getPermissionList(adminId);
    }

    /**
     * 后台-登录
     *
     * @return
     */
    @Override
    public String login(UmsAdminLoginReq req) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());
            if (!passwordEncoder.matches(req.getPassword(), userDetails.getPassword())) {
                throw new BusinessException(BusinessExceptionEnum.WRONG_PASSWORD);
            }
            // 更新最后一次登录时间
            UmsAdmin umsAdminDB = getAdminByUsername(req.getUsername());
            umsAdminDB.setLoginTime(new Date());
            umsAdminMapper.updateByPrimaryKeySelective(umsAdminDB);

            // 往登录日志表中插入数据
            insertLoginLog(umsAdminDB);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            throw new BusinessException(BusinessExceptionEnum.WRONG_PASSWORD);
        }
        return token;
    }

    /**
     * 后台-获取用户列表
     *
     * @param req
     * @return
     */
    @Override
    public PageBean list(UmsAdminListReq req) {
        PageHelper.startPage(req.getCurrent(), req.getPageSize());

        List<UmsAdminListResp> umsAdminList = umsAdminMapperDao.selectList(req);
        return new PageBean<>(umsAdminList);
    }

    /**
     * 后台-新增/更新用户
     * @param req
     */
    @Override
    public void saveUser(UmsAdminSaveReq req) {
        UmsAdmin umsAdmin = CopyUtil.copy(req, UmsAdmin.class);
        UmsAdmin umsAdminDB = getAdminByUsername(req.getUsername());
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            if (umsAdminDB != null) {
                throw new BusinessException(BusinessExceptionEnum.NAME_EXIST);
            }
            umsAdmin.setCreateTime(new Date());
            // 设置初始密码为 123456，并加密
            String encodePassword = passwordEncoder.encode("123456");
            umsAdmin.setPassword(encodePassword);

            umsAdminMapper.insertSelective(umsAdmin);
        } else {
            // 更新
            if (umsAdminDB != null && !umsAdminDB.getId().equals(req.getId())) {
                throw new BusinessException(BusinessExceptionEnum.NAME_EXIST);
            }
            umsAdminMapper.updateByPrimaryKeySelective(umsAdmin);
        }
    }

    /**
     * 后台-批量删除用户
     * @param ids
     */
    @Override
    public void deleteUser(Long[] ids) {
        umsAdminMapperDao.batchDelete(ids);
    }

    /**
     * 分配角色
     * @param adminId
     * @param roleIds
     */
    @Override
    public void assignRole(Long adminId, List<Long> roleIds) {
        // 删除原有的关系
        umsAdminRoleRelationMapperDao.deleteAdminRoleRelationByAdminId(adminId);
        // 建立新的关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            for (Long roleId: roleIds) {
                UmsAdminRoleRelation roleRelation = new UmsAdminRoleRelation();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                umsAdminRoleRelationMapper.insert(roleRelation);
            }
        }
    }

    /**
     * 添加登录日志
     * @param umsAdmin
     */
    public void insertLoginLog(UmsAdmin umsAdmin) {
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(umsAdmin.getId());
        loginLog.setCreateTime(new Date());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(RequestUtil.getRequestIp(request));
        umsAdminLoginLogMapper.insert(loginLog);
    }

}
