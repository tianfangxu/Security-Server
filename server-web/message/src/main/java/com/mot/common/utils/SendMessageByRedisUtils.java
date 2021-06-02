package com.mot.common.utils;

import com.alibaba.fastjson.JSON;
import com.mot.common.constant.RedisConstant;
import com.mot.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class SendMessageByRedisUtils implements SendMessageInterface {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public boolean send(MessageModel model) {
        redisTemplate.convertAndSend(RedisConstant.redisChannelName, JSON.toJSONString(model));
        return false;
    }
}
