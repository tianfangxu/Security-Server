package com.mot.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author tianfx
 * @date 2021/6/3 4:49 下午
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
