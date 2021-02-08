package com.xwc.config.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;

/**
 * 作者：CC 时间：2020/8/20 15:46 描述：
 */
@Configuration
@EnableWebMvc
public class AppWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    @Autowired
    private DefaultRequestResponseBodyMethodProcessor defaultRequestResponseBodyMethodProcessor;

    /**
     * Controller 方法参数注入
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(defaultRequestResponseBodyMethodProcessor);
    }


    @Bean
    public DefaultRequestResponseBodyMethodProcessor defaultRequestResponseBodyMethodProcessor(
            List<HttpMessageConverter<?>> httpMessageConverterList) {
        return new DefaultRequestResponseBodyMethodProcessor();
    }
}
