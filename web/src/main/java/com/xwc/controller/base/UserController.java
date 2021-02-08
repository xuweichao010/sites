package com.xwc.controller.base;

import com.xwc.commons.model.JsonMessage;
import com.xwc.config.mvc.EncryptBody;
import com.xwc.controller.base.dto.user.UserDto;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 创建人：徐卫超 创建时间：2019/4/30  10:05 业务： 功能：
 */
@RestController
@RequestMapping("/user")
@Api(tags = "base_user", description = "用户管理")
public class UserController {


    @PostMapping()
    @ApiOperation("添加用户")
    public JsonMessage<Void> addUser(@Validated @EncryptBody UserDto body) {
        return JsonMessage.succeed();
    }

    @PostMapping("/1")
    @ApiOperation("添加用户")
    public JsonMessage<Void> addUser1(@Validated UserDto body) {
        return JsonMessage.succeed();
    }

}
