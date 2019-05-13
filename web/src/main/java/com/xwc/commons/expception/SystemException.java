package com.xwc.commons.expception;

/**
 * 创建人：徐卫超
 * 创建时间：2019/3/18  20:01
 * 业务：异常处理
 * 功能：吧必须捕获异常转换成运行异常，所有异常有异常处理器统一处理
 */
public class SystemException extends BaseExpception {

    public SystemException(Throwable cause) {
        super(cause);
    }
}
