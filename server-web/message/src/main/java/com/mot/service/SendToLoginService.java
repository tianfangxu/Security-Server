package com.mot.service;

import com.mot.common.utils.UIDUtils;
import com.mot.model.MessageModel;
import com.mot.service.MessageService;
import org.springframework.stereotype.Component;

@Component
public class SendToLoginService implements MessageService {
    @Override
    public void excutor(MessageModel model) {
        System.out.println("调用了登录接口:"+ UIDUtils.getSessionId());
    }
}
