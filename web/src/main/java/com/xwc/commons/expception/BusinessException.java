package com.xwc.commons.expception;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/15  15:56
 * 功能：业务异常,只需要对这类异常进行常规操作,返回到调用者即可
 */
public class BusinessException extends BaseExpception {
    public BusinessException(String message, String... desc) {
        super(message, desc);
    }
}
