package com.mot.service;

import com.mot.factory.send.SendMessageObjectFactory;
import com.mot.model.MessageModel;
import com.mot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendToTextService implements MessageService {

    @Autowired
    SendMessageObjectFactory sendMessageObjectFactory;

    @Override
    public void excutor(MessageModel model) {
        sendMessageObjectFactory.getInstance().send(model);
    }
}
