package com.mot.service.impl;

import com.mot.service.ReceiverService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class ReceiverByLocalService implements ReceiverService {

    @Override
    public void handleMessage(String msg) {
        System.out.println("接受到消息："+msg);
    }
}
