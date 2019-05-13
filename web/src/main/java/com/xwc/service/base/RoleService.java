package com.xwc.service.base;

import com.alibaba.fastjson.JSONObject;
import com.xwc.commons.expception.BusinessException;
import com.xwc.controller.base.dto.role.RoleFilterDto;
import com.xwc.entity.base.Org;
import com.xwc.entity.base.Role;
import com.xwc.entity.base.RoleMenu;
import com.xwc.mapper.MenuMapper;
import com.xwc.mapper.OrgMapper;
import com.xwc.mapper.RoleMapper;
import com.xwc.mapper.RoleMenuMapper;
import com.xwc.service.auth.AuthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 创建人：徐卫超
 * 创建时间：2019/5/5  11:42
 * 业务：
 * 功能：
 */
@Component
public class RoleService {

    @Autowired
    private AuthInfoService authInfoService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private OrgMapper orgMapper;


    @Transactional
    public void add(Role role) {
        Org org = authInfoService.org();
        if (!role.getOrgCode().startsWith(org.getCode()))
            throw new BusinessException("权限受限", org.getCode(), role.getOrgCode());
        Role nameRole = roleMapper.byNameAndOrgCode(role.getName(), role.getOrgCode());
        if (nameRole != null) throw new BusinessException("角色已存在", role.getName(), role.getOrgCode());
        org = orgMapper.selectKey(role.getOrgCode());
        role.setOrgName(org.getName());
        roleMapper.insert(role);
    }

    @Transactional
    public void update(Role role) {
        Role idRole = roleMapper.selectKey(role.getId());
        if (idRole == null) throw new BusinessException("角色不存在", role.getId().toString());
        Role nameRole = roleMapper.byNameAndOrgCode(role.getName(), role.getOrgCode());
        if (nameRole != null && !nameRole.getId().equals(role.getId()))
            throw new BusinessException("角色已存在", role.getName(), role.getOrgCode());
        Org org = authInfoService.org();
        if (!role.getOrgCode().startsWith(org.getCode()))
            throw new BusinessException("权限受限", org.getCode(), role.getOrgCode());
        org = orgMapper.selectKey(role.getOrgCode());
        role.setOrgName(org.getName());
        roleMapper.insert(role);
        roleMenuMapper.delete(role.getId());
    }

    public void grandMenu(HashSet<Long> menuIdSet, Long roleId) {
        Role role = roleMapper.selectKey(roleId);
        if (role == null) throw new BusinessException("角色不存在", roleId.toString());
        List<String> existMenuIds = menuMapper.listByIds(menuIdSet);
        if (existMenuIds.size() != menuIdSet.size()) {
            menuIdSet.removeAll(existMenuIds);
            throw new BusinessException("按钮或菜单不存在", JSONObject.toJSONString(menuIdSet));
        }
        List<RoleMenu> menuList = menuIdSet.stream().map(menuId -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleId);
            return roleMenu;
        }).collect(Collectors.toList());
        roleMenuMapper.insertBatch(menuList);
    }

    public List<Role> list(RoleFilterDto filter) {
        return  roleMapper.list(filter);
    }

    public Long count(RoleFilterDto filter) {
        return roleMapper.count(filter);
    }
}
