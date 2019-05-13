package com.xwc.service.base;

import com.alibaba.fastjson.JSONObject;
import com.xwc.commons.expception.BusinessException;
import com.xwc.controller.base.dto.user.UserFilterDto;
import com.xwc.entity.base.*;
import com.xwc.mapper.OrgMapper;
import com.xwc.mapper.RoleMapper;
import com.xwc.mapper.UserMapper;
import com.xwc.mapper.UserRoleMapper;
import com.xwc.service.auth.AuthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/30  10:07
 * 业务：
 * 功能：
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrgMapper orgMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private AuthInfoService authInfoService;

    @Transactional
    public void add(User user) {
        User temp = userMapper.byAccount(user.getAccount());
        if (null != temp) throw new BusinessException("用户已存在", user.getAccount());
        Org org = orgMapper.selectKey(user.getOrgCode());
        if (org == null) throw new BusinessException("机构不存在", "机构代码" + user.getOrgCode());
        user.setOrgCode(org.getCode());
        user.setOrgName(org.getName());
        userMapper.insert(user);
    }

    public User getUser(Long userId) {
        User user = userMapper.selectKey(userId);
        if (user == null) throw new BusinessException("用户不存在", String.valueOf(userId));
        return user;
    }

    @Transactional
    public void update(User user) {
        User temp = userMapper.selectKey(user.getId());
        if (null == temp) throw new BusinessException("用户不存在", user.getAccount());
        temp = userMapper.byAccount(user.getAccount());
        if (null != temp && !temp.getId().equals(user.getId())) throw new BusinessException("账号已存在", user.getAccount());
        Org org = orgMapper.selectKey(user.getOrgCode());
        if (org == null) throw new BusinessException("机构不存在", "机构代码" + org.getCode());
        user.setOrgCode(org.getCode());
        user.setOrgName(org.getName());
        userMapper.update(user);
    }

    public List<User> list(UserFilterDto filter) {
        return userMapper.list(filter);
    }

    public Long count(UserFilterDto filterDto) {
        return userMapper.count(filterDto);
    }

    @Transactional
    public void del(String account) {
        User user = userMapper.byAccount(account);
        if (null == user) throw new BusinessException("用户不存在", account);
        userMapper.delete(user.getId());
        roleMapper.delete(user.getId());
    }

    @Transactional
    public void grantRole(Long userId, Set<Long> roleIdSet) {
        User user = userMapper.selectKey(userId);
        if (null == user) throw new BusinessException("用户不存在", String.valueOf(userId));
        Org org = authInfoService.org();
        List<Long> existRoleId = roleMapper.listByRoleId(roleIdSet, org.getCode());
        if (roleIdSet.size() != existRoleId.size()) {
            roleIdSet.removeAll(existRoleId);
            throw new BusinessException("角色不存在", JSONObject.toJSONString(roleIdSet));
        }
        userRoleMapper.delete(user.getId());
        List<UserRole> userRoleList = roleIdSet.stream().map(roleId -> {
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(user.getId());
            return userRole;
        }).collect(Collectors.toList());
        userRoleMapper.insertBatch(userRoleList);
    }

    public List<Role> roleByUser(Long userId) {
        return userMapper.roleByUser(userId);
    }

    public List<Menu> listMenuByUser(Long userId) {
        return userMapper.listMenuByUser(userId);
    }


}
