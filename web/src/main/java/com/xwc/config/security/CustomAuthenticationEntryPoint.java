package com.xwc.config.security;

import com.alibaba.fastjson.JSONObject;
import com.xwc.commons.model.JsonMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 创建人：徐卫超
 * 创建时间：2019/5/6  16:40
 * 业务：
 * 功能：
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {


    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(JsonMessage.failed("请重新登录", JsonMessage.NOT_AUTHEN, authException.getMessage())));
    }
}
