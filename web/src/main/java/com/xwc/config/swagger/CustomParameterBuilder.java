package com.xwc.config.swagger;

import com.xwc.config.mvc.EncryptBody;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;

@Component
@Order
public class CustomParameterBuilder implements ParameterBuilderPlugin {
    @Override
    public void apply(ParameterContext parameterContext) {
        System.out.println("------------------------------------------------");
        //处理 自定义注解 在页面上的样式
        String parameterType = findType(parameterContext);
        if (StringUtils.hasText(parameterType)) {
            parameterContext.parameterBuilder().parameterType(parameterType);
        }
    }


    public static String findType(ParameterContext parameterContext) {
        ResolvedMethodParameter resolvedMethodParameter = parameterContext.resolvedMethodParameter();
        if (resolvedMethodParameter.hasParameterAnnotation(EncryptBody.class)) {
            return "body";
        }
        return null;
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }
}
