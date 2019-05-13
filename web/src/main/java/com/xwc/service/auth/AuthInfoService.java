package com.xwc.service.auth;

import com.alibaba.fastjson.JSONObject;
import com.xwc.commons.expception.DataException;
import com.xwc.entity.base.Org;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/27  9:48
 * 业务：
 * 功能：管理用户的登录后的信息
 */
@Component
public class AuthInfoService {
    /**
     * 获取用户的机构信息
     *
     * @return
     */
    public Org org() {
        return userInfo().getOrg();
    }

    public Long userId() {
        return userInfo().getId();
    }



    private IUser userInfo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof IUser))
            throw new DataException("用户信息错误", "用户类型转换失败" + JSONObject.toJSONString(principal));
        return (IUser) principal;
    }
}
