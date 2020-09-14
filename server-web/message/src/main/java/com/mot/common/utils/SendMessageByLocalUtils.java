package com.mot.common.utils;

import com.mot.model.MessageModel;
import com.mot.service.impl.ReceiverByLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class SendMessageByLocalUtils implements SendMessageInterface {

    @Autowired
    ReceiverByLocalService receiverByLocalService;

    @Override
    public boolean send(MessageModel model) {
        receiverByLocalService.handleMessage(model.getBody());
        return true;
    }
}
