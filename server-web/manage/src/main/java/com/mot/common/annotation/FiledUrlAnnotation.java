package com.mot.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author tianfx
 * @Date 2020/9/20
 * 访问接口注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FiledUrlAnnotation {

    boolean value() default true;

    /**
     * 写入日志
     * @return @true 需要写入日志；@false 无需写入日志
     */
    boolean writeLogs() default true;

    /**
     * 权限访问
     * @return @true 需要权限才能访问；@false 无权限
     */
    boolean hasPermit() default true;
}
