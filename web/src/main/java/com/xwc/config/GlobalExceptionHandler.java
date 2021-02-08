package com.xwc.config;

import com.xwc.commons.expception.BusinessException;
import com.xwc.commons.model.JsonMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


/**
 * 创建人：徐卫超
 * 时间: 2018/5/12
 * 描述：全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //处理业务错误
    @ResponseBody
    @ExceptionHandler(value = {BusinessException.class, })
    public JsonMessage BusinessExceptionHandler(HttpServletRequest request, Exception exception) {
        return JsonMessage.failed(exception.getMessage());
    }


    //处理数据校验错误
    @ResponseBody
    @ExceptionHandler(value = {BindException.class})
    public JsonMessage validataExceptionHandler(HttpServletRequest request, Exception exception) {
        BindException bindException = (BindException) exception;
        List<ObjectError> errors = bindException.getAllErrors();
        if (errors.isEmpty()) {
            return JsonMessage.failed(errors.get(0).getDefaultMessage());
        } else {
            return JsonMessage.failed("校验错误");
        }
    }



}
