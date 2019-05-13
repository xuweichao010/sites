package com.xwc.controller;

import com.xwc.commons.model.JsonMessage;
import com.xwc.config.security.anno.Privilege;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建人：徐卫超
 * 创建时间：2019/3/14  15:29
 * 业务：
 * 功能：
 */
@RestController
@RequestMapping("/demo")
@Api(tags = "demo", description = "案例")
public class DemoController {

    @GetMapping("")
    @ApiOperation("获取信息")
    @Privilege(value = "1111")
    public JsonMessage<String> demo() {
        return JsonMessage.succeed("this is demo ");
    }
}