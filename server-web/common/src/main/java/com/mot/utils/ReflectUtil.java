package com.mot.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author tianfx
 * @date 2021/5/14 4:24 下午
 */
public class ReflectUtil {

    public static void invokeStaticMethodWithNoparamsNoretrun(Class clazz,String methodName){
        invokeMethod(clazz,null,null,methodName,null);
    }
    public static <T> T invokeStaticMethodWithNoparams(Class clazz,Class<T> result,String methodName){
        return invokeMethod(clazz,null,result,methodName,null);
    }
    public static void invokeStaticMethodWithNoretrun(Class clazz,String methodName,Object[] params){
        invokeMethod(clazz,null,null,methodName,params);
    }
    public static <T> T invokeStaticMethod(Class clazz,Class<T> result,String methodName,Object[] params){
        return invokeMethod(clazz,null,result,methodName,params);
    }
    public static void invokeMethodWithNoparamsNoretrun(Class clazz,Object thisObject,String methodName){
        invokeMethod(clazz,thisObject,null,methodName,null);
    }
    public static <T> T invokeMethodWithNoparams(Class clazz,Object thisObject,Class<T> result,String methodName){
        return invokeMethod(clazz,thisObject,result,methodName,null);
    }
    public static void invokeMethodWithNoretrun(Class clazz,Object thisObject,String methodName,Object[] params){
        invokeMethod(clazz,thisObject,null,methodName,params);
    }
    /**
     * invoke private method
     * @author: tianfx
     * @date: 2021/5/14 4:55 下午
     */
    public static <T> T invokeMethod(Class clazz,Object thisObject,Class<T> result,String methodName,Object[] params){
        Method method = null;
        try {
            if (params == null || params.length == 0){
                method = clazz.getDeclaredMethod(methodName);
            }else{
                Class[] paramTypes = new Class[params.length];
                for (int i = 0; i < params.length; i++) {
                    paramTypes[i] = params[i].getClass();
                }
                method = clazz.getDeclaredMethod(methodName,paramTypes);
            }
            method.setAccessible(true);
            if (result == null) {
                method.invoke(thisObject,params);
                return null;
            }else{
                return (T)method.invoke(thisObject,params);
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * set Object field
     * @author: tianfx
     * @date: 2021/5/14 4:56 下午
     */
    public static void invokeField(Class clazz,Object thisObject,String fieldName,Object value){
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(thisObject,value);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
