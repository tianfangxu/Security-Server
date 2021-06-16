package com.mot.common.utils;

/**
 * @author tianfx
 * @version 1.0
 * @date 2021/4/27 1:40 下午
 * @description java类
 */
public class TaskProjectClassLoader extends ClassLoader{
    @Override
    public Class<?> loadClass(String s) throws ClassNotFoundException {
        return super.loadClass(s);
    }
}
