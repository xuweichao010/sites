package com.xwc.entity.base;

import com.xwc.controller.base.dto.user.UserDto;
import com.xwc.esbatis.anno.table.PrimaryKey;
import com.xwc.esbatis.anno.table.Table;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/27  10:17
 * 业务：
 * 功能：
 */
@Table("t_user")
@SuppressWarnings("unused")
public class User implements Serializable {
    private static final long serialVersionUID = -4279599274719815691L;
    /**
     * 用户ID
     */
    @PrimaryKey
    private Long id;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名
     */
    private String name;
    /**
     * 机构名
     */
    private String orgName;
    /**
     * 机构代码
     */
    private String orgCode;

    public static User convert(UserDto body) {
        User user = new User();
        BeanUtils.copyProperties(body, user);
        return user;
    }

    /**
     * 用户ID
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 用户ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 账号
     */
    public String getAccount() {
        return this.account;
    }

    /**
     * 账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 用户名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 机构名
     */
    public String getOrgName() {
        return this.orgName;
    }

    /**
     * 机构名
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 机构代码
     */
    public String getOrgCode() {
        return this.orgCode;
    }

    /**
     * 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }


}
