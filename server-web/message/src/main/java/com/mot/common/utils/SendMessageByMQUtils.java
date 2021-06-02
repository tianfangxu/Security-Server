package com.mot.common.utils;

import com.alibaba.fastjson.JSON;
import com.mot.common.constant.RabbitMqConstant;
import com.mot.model.MessageModel;
import com.mot.service.ReceiverByRabbitService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class SendMessageByMQUtils implements SendMessageInterface {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 此处注入 无引用，但需要实例化TopicReceiverService类
     * 所以不能省略
     */
    @Autowired
    ReceiverByRabbitService receiverByRabbitService;

    @Override
    public boolean send(MessageModel model) {
        rabbitTemplate.convertAndSend(RabbitMqConstant.exchangeName,
                RabbitMqConstant.routerKey,
                JSON.toJSONString(model));
        return true;
    }
}
