package com.mot.service.impl;

import com.mot.service.ReceiverService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component()
@RabbitListener(queues = "${server.message.uid}")
@Lazy
public class ReceiverByRabbitService implements ReceiverService {

    @Override
    @RabbitHandler
    public void handleMessage(String msg) {
        System.out.println("接收到的消息："+msg);
    }
}
