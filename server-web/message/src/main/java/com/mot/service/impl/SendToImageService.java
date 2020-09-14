package com.mot.service.impl;

import com.mot.common.utils.UIDUtils;
import com.mot.model.MessageModel;
import com.mot.service.MessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendToImageService implements MessageService {

    @Override
    public void excutor(MessageModel model) {
        System.out.println("调用了图片消息:"+ UIDUtils.getSessionId());
    }
}
