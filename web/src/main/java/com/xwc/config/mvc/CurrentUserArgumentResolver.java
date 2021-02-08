package com.xwc.config.mvc;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 作者：CC 时间：2020/8/20 15:40 描述：参数注入
 */

public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasMethodAnnotation(EncryptBody.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer modelAndView,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (webRequest instanceof ServletWebRequest) {
            HttpServletRequest request = ((ServletWebRequest) webRequest).getRequest();

        }
        return null;
    }

}