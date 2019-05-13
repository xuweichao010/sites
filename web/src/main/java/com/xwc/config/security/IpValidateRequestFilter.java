package com.xwc.config.security;

import com.alibaba.fastjson.JSONObject;
import com.xwc.commons.model.JsonMessage;
import com.xwc.service.CacheService;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 创建人：徐卫超
 * 创建时间：2019/5/7  11:14
 * 业务：
 * 功能：
 */
@Order(100)
public class IpValidateRequestFilter extends GenericFilterBean {

    private CacheService cacheService;

    public IpValidateRequestFilter(CacheService cacheService) {
        this.cacheService = cacheService;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //校验IP
        if (authentication instanceof OAuth2Authentication) {
            if (authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
                OAuth2AuthenticationDetails webAuthen = (OAuth2AuthenticationDetails) authentication.getDetails();
                String value = cacheService.getValue(CacheService.AUT_HOME_IP + webAuthen.getTokenValue());
                if (!webAuthen.getRemoteAddress().equals(value)) {
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(JSONObject.toJSONString(JsonMessage.failed("发现IP变化请重新登录", JsonMessage.NOT_AUTHEN, null)));
                    return;
                }
            }
            authentication.getDetails();
        }
        chain.doFilter(request, response);
    }
}
