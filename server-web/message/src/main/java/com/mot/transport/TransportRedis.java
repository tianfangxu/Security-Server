package com.mot.transport;

import com.alibaba.fastjson.JSON;
import com.mot.common.constant.RedisConstant;
import com.mot.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author tianfx
 * @date 2021/6/15 6:17 下午
 */
@Component
public class TransportRedis implements TransportHolder{

    @Autowired(required = false)
    RedisTemplate redisTemplate;

    @Override
    public void transport(MessageModel model) {
        redisTemplate.convertAndSend(RedisConstant.redisChannelName, JSON.toJSONString(model));
    }
}
