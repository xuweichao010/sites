package com.xwc.esbatis.anno.condition.enhance;

import com.xwc.esbatis.anno.enums.ConditionEnum;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/24  13:01
 * 业务：
 * 功能：
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {
    int index() default 99;

    String colum() default "";

    ConditionEnum type() default ConditionEnum.NOT_NULL;
}
