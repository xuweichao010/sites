package com.xwc.service.auth;

import com.xwc.commons.expception.BusinessException;
import com.xwc.entity.base.Menu;
import com.xwc.entity.base.User;
import com.xwc.mapper.MenuMapper;
import com.xwc.mapper.OrgMapper;
import com.xwc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/28  10:44
 * 业务：
 * 功能：
 */
@Component
public class AuthUserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrgMapper orgMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.byAccount(username);
        if (user == null) throw new BusinessException("用户名不存在", username);
        List<SimpleGrantedAuthority> authorities;
        if (user.getId() == 1) {
            authorities = menuMapper.findAll().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        } else {
            List<Menu> menus = userMapper.listMenuByUser(user.getId());
            authorities = menus.stream().map(menu -> new SimpleGrantedAuthority(menu.getId().toString())).collect(Collectors.toList());
        }
        IUser iUser = new IUser(user.getAccount(), user.getPassword(), authorities);
        iUser.setOrg(orgMapper.selectKey(user.getOrgCode()));
        iUser.setId(user.getId());
        return iUser;
    }
}
