package com.mot.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author tianfx
 * @Date 2020/9/20
 * 是否写入日志
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface LogsWriteAnnotation {

    boolean value() default true;
}
