package com.xwc.config.security.enums;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/26  10:53
 * 业务：
 * 功能：访问类型
 */
public enum AccessEnum {
    USER("user"),
    CLIENT("client");

    private String code;

    AccessEnum(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

}
