package com.mot.common.aspect;

import com.mot.model.ResultBaseModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectLog {

    @Pointcut("execution(public * com.mot.controller.*.*(..))")
    public void aspectLog(){}

    /**
     * 前置通知
     */
    @Before("aspectLog()")
    public void beforeLog(){
        System.out.println("before");
    }

    @After("aspectLog()")
    public void afterLog(){
        System.out.println("after");
    }

    @Around("aspectLog()")
    public Object aroundLog(ProceedingJoinPoint pjp){
        Object proceed = null;
        try {
            System.out.println("前置----");
            proceed = pjp.proceed();
            System.out.println("后置----");
        } catch (Throwable throwable) {
            System.out.println("异常----");
            throwable.printStackTrace();
        }
        return proceed;
    }
}
