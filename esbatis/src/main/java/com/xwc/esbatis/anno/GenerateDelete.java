package com.xwc.esbatis.anno;

import com.xwc.esbatis.anno.enums.SqlOperationType;

import java.lang.annotation.*;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/24  10:56
 * 业务：
 * 功能：chuan
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@GeneratSql
public @interface GenerateDelete {

    SqlOperationType value() default SqlOperationType.BASE_DELETE;

    String colums() default "";
}
