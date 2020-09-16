package com.mot.common.aspect;

import com.mot.config.properties.GlobalSettingConfig;
import com.mot.model.AuthUserModel;
import com.mot.model.ParamLogActionModel;
import com.mot.service.LogActionService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class AspectLog {

    @Autowired
    LogActionService logActionService;

    @Autowired
    GlobalSettingConfig globalSettingConfig;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * com.mot.controller.*.*(..))")
    public void aspectLog(){}

    @Around("aspectLog()")
    public Object aroundLog(ProceedingJoinPoint point){
        Object proceed = null;
        try {
            proceed = point.proceed();
        } catch (Throwable throwable) {
            logger.error("访问异常："+throwable.getMessage(),throwable);
            throwable.printStackTrace();
        }
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) (RequestContextHolder.getRequestAttributes());
            HttpServletRequest request = attributes.getRequest();
            String requestURI = request.getRequestURI();
            logger.debug("访问路径："+requestURI);
            logger.debug("访问参数："+point.getArgs()[0]);
            logger.debug("访问返回值："+proceed);
            ParamLogActionModel model = new ParamLogActionModel();
            model.setServer(globalSettingConfig.serverName);
            model.setUrl(requestURI);
            model.setRequest(String.valueOf(point.getArgs()[0]));
            model.setResponse(String.valueOf(proceed));
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null){
                AuthUserModel currentUser = (AuthUserModel) authentication.getPrincipal();
                model.setCreateUserName(currentUser.getRealName());
                model.setCreateUserId(currentUser.getId());
            }
            model.setCreateUserIp(getIpAddress(request));
            model.setCreateUserClient(request.getHeader("User-Agent"));
            logActionService.insertLog(model);
        }catch (RuntimeException e){e.printStackTrace();}
        return proceed;
    }

    public String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            return ip.split(",")[0];
        } else {
            return ip;
        }
    }
}
