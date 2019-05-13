package com.xwc.controller.base.dto.user;

import com.xwc.entity.base.User;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/30  10:08
 * 业务：
 * 功能：
 */
public class UserDto {

    @ApiModelProperty("用户ID")
    private Long id;

    @ApiModelProperty("账号")
    @NotNull(message = "账号不能为空")
    private String account;

    @ApiModelProperty("密码")
    @NotNull(message = "密码不能为空")
    private String password;

    @ApiModelProperty("用户名")
    @NotNull(message = "用户名不能为空")
    private String name;

    @ApiModelProperty("机构名")
    @NotNull(message = "机构不能为空")
    private String orgName;

    @ApiModelProperty("机构代码")
    @NotNull
    @NotNull(message = "机构不能为空")
    private String orgCode;


    public static UserDto convert(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
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
