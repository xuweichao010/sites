package com.xwc.config.mvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xwc.commons.model.JsonMessage;
import com.xwc.controller.base.dto.user.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Component
@RestControllerAdvice
public class DemoResponseBodyAdvice implements ResponseBodyAdvice {
    @Autowired
    private List<HttpMessageConverter> httpMessageConverterList;


    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return returnType.hasMethodAnnotation(Encrypt.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof JsonMessage) {
            for (HttpMessageConverter messageConverter : httpMessageConverterList) {
                if (messageConverter instanceof MappingJackson2HttpMessageConverter) {
                    ObjectMapper objectMapper = ((MappingJackson2HttpMessageConverter) messageConverter)
                            .getObjectMapper();
                    try {
                        return JsonMessage.succeed(Base64.getEncoder()
                                .encodeToString(objectMapper.writeValueAsString(new UserDto()).getBytes()));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return body;
    }
}
