package com.xwc.config.security.anno;

import com.xwc.config.security.enums.AccessEnum;

import java.lang.annotation.*;

/**
 * 创建人：徐卫超
 * 创建时间：2019/3/19  10:08
 * 业务：
 * 功能：
 */
@Documented
@Target({ElementType.METHOD})     //只能使用在：类、接口、注解、枚举
@Retention(RetentionPolicy.RUNTIME)     //在运行时有效
public @interface Privilege {
    String value();

    AccessEnum[] access() default {AccessEnum.USER, AccessEnum.CLIENT};

    boolean ignore() default false;
}
