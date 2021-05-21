package com.mot.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author tianfx
 * @date 2021/5/13 5:18 下午
 */
public class AnnotationUtil {

    /**
     * get class type annotation instance
     * @author: tianfx
     * @date: 2021/5/13 5:22 下午
     * @param targetClass: Use class with annotation
     * @param targetAnnotation:  annotation in target class
     * @return {@link T}: Annotation instance
     */
    public static<T> T getClassAnnotation(Class targetClass,Class<T> targetAnnotation){
        T t = (T)targetClass.getAnnotation(targetAnnotation);
        if (t == null){
            throw new RuntimeException("unknown Annotation Type:"+targetAnnotation.getName());
        }
        return t;
    }

    /**
     * get class target method annotation
     * @author: tianfx
     * @date: 2021/5/13 5:59 下午
     */
    public static<T> T getMethodAnnotation(Class targetClass,Class<T> targetAnnotation,String methodName,Class[] paramTypes){
        Method method = null;
        try {
            method = targetClass.getDeclaredMethod(methodName,paramTypes);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("class has no Method:"+methodName);
        }
        T t = (T)method.getAnnotation((Class)targetAnnotation);
        if (t == null){
            throw new RuntimeException("unknown Annotation Type:"+targetAnnotation.getName());
        }
        return t;
    }
    /**
     * get class target Field annotation
     * @author: tianfx
     * @date: 2021/5/14 10:29 上午
     */
    public static<T> T getFieldAnnotation(Class targetClass,Class<T> targetAnnotation,String field){
        Field declaredField = null;
        try {
            declaredField = targetClass.getDeclaredField(field);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("class has no Field:"+field);
        }
        T t = (T)declaredField.getAnnotation((Class) targetAnnotation);
        if (t == null){
            throw new RuntimeException("unknown Annotation Type:"+targetAnnotation.getName());
        }
        return t;
    }
}
