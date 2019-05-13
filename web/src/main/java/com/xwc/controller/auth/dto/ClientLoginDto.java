package com.xwc.controller.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 创建人：徐卫超
 * 创建时间：2019/3/18  17:55
 * 业务：
 * 功能：
 */
@ApiModel(value = "ClientLogin", description = "客户端登录请求")
public class ClientLoginDto {
    @ApiModelProperty("客户端ID")
    @NotNull(message = "客户端ID不能为空")
    private String clientId;
    @ApiModelProperty("客户端授权码")
    @NotNull(message = "授权码不能为空")
    private String clientSecret;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
