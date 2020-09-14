package com.mot.factory.send;

import com.mot.config.properties.GlobalSettingConfig;
import com.mot.common.utils.SendMessageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 获取发送消息的实例类型
 */

@Component
public class SendMessageObjectFactory {

    @Autowired
    ApplicationContext applicationContext;

    SendMessageInterface sendMessageInterface;

    public SendMessageInterface getInstance(){
        return sendMessageInterface == null?
                (sendMessageInterface = applicationContext.getBean(GlobalSettingConfig.getPropValue("send_message_bean_name"),
                SendMessageInterface.class)): sendMessageInterface;
    }

}
