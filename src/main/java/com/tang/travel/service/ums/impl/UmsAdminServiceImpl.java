package com.tang.travel.service.ums.impl;

import com.github.pagehelper.PageHelper;
import com.tang.travel.exception.BusinessException;
import com.tang.travel.exception.BusinessExceptionEnum;
import com.tang.travel.mbg.mapper.UmsAdminMapper;
import com.tang.travel.mbg.model.UmsAdmin;
import com.tang.travel.mbg.model.UmsAdminExample;
import com.tang.travel.mbg.model.UmsPermission;
import com.tang.travel.model.dao.UmsPermissionMapperDao;
import com.tang.travel.model.req.ums.UmsAdminListReq;
import com.tang.travel.model.req.ums.UmsAdminLoginReq;
import com.tang.travel.model.req.ums.UmsAdminRegisterReq;
import com.tang.travel.model.resp.ums.UmsAdminListResp;
import com.tang.travel.service.ums.UmsAdminService;
import com.tang.travel.util.CopyUtil;
import com.tang.travel.util.JwtTokenUtil;
import com.tang.travel.util.PageBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Resource
    UmsAdminMapper umsAdminMapper;
    @Resource
    UmsPermissionMapperDao umsPermissionMapperDao;
    @Resource
    UserDetailsService userDetailsService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 后台-根据用户名查询指定用户详细信息
     * @param username
     * @return
     */
    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        UmsAdminExample.Criteria criteria = umsAdminExample.createCriteria();
        criteria.andUsernameEqualTo(username);

        List<UmsAdmin> adminList = umsAdminMapper.selectByExample(umsAdminExample);
        if (CollectionUtils.isEmpty(adminList)) {
            return null;
        } else {
            return adminList.get(0);
        }
    }

    /**
     * 后台-注册
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
     * @param adminId
     * @return
     */
    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return umsPermissionMapperDao.getPermissionList(adminId);
    }

    /**
     * 后台-登录
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
     * @param req
     * @return
     */
    @Override
    public PageBean list(UmsAdminListReq req) {
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        UmsAdminExample.Criteria criteria = umsAdminExample.createCriteria();
        // 用户名与昵称模糊查询
        if (!ObjectUtils.isEmpty(req.getUsername())) {
            criteria.andUsernameLike('%' + req.getUsername() + '%');
        }
        if (!ObjectUtils.isEmpty(req.getNickName())) {
            criteria.andNickNameLike('%' + req.getNickName() + '%');
        }

        PageHelper.startPage(req.getCurrent(), req.getPageSize());

        List<UmsAdmin> umsAdminList = umsAdminMapper.selectByExample(umsAdminExample);
        // 列表复制
        List<UmsAdminListResp> list = CopyUtil.copyList(umsAdminList, UmsAdminListResp.class);
        return new PageBean<>(list);
    }
}
