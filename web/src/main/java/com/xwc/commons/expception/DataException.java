package com.xwc.commons.expception;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/31  17:55
 * 功能：用来处理系统数据异常（如：数据缺失。。。。全局异常需要处理并记录）
 * 业务：
 */
public class DataException extends BusinessException {
    public DataException(String message, String desc) {
        super(message, desc);
    }
}
