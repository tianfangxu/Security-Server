package com.mot.handler.message;

import com.mot.common.enums.SessionTypeEnums;
import com.mot.common.utils.SpringContextHolder;
import org.springframework.context.ApplicationContext;

/**
 * des:
 * @author tianfx
 * @date 2021/6/15 2:31 下午
 */
public class MessageHandlerFactory {

    public static MessageHandlerHolder getHolders(Integer code){
        ApplicationContext applicationContext = SpringContextHolder.getApplicationContext();
        MessageHandlerHolder holder = null;
        SessionTypeEnums[] typeEnums = SessionTypeEnums.values();
        for (SessionTypeEnums typeEnum : typeEnums) {
            if (typeEnum.code == code.intValue()){
                holder = applicationContext.getBean(typeEnum.handler);
            }
        }
        return holder;
    }
}
