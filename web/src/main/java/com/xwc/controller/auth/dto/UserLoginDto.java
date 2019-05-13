package com.xwc.controller.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 创建人：徐卫超
 * 创建时间：2019/3/18  15:19
 * 业务：认证模块
 * 功能：用户登录
 */
@ApiModel(value = "UserLogin", description = "用户登录实体")
public class UserLoginDto {
    @ApiModelProperty(value = "用户名", example = "admin")
    @NotNull(message = "用户名不能为空")
    private String username;
    @ApiModelProperty(value = "密码", example = "admin")
    @NotNull(message = "密码不能为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
