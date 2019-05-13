package com.xwc.config.security;

import org.springframework.security.core.AuthenticationException;

/**
 * 创建人：徐卫超
 * 创建时间：2019/5/7  11:11
 * 业务：
 * 功能：
 */
public class ValidateIpException extends AuthenticationException {
    public ValidateIpException(String msg) {
        super(msg);
    }
}
