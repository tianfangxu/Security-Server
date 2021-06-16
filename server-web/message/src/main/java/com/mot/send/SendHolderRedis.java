package com.mot.send;

import com.alibaba.fastjson.JSON;
import com.mot.handler.message.MessageHandlerFactory;
import com.mot.model.MessageModel;
import org.springframework.stereotype.Component;

/**
 * @author tianfx
 * @date 2021/6/15 6:21 下午
 * redis队列，绑定关系：{@link com.mot.config.redis.RedisConfig}
 */
@Component
public class SendHolderRedis implements SendHolder{
    @Override
    public void send(String info) {
        MessageModel model = JSON.parseObject(info, MessageModel.class);
        MessageHandlerFactory.getHolders(model.getType()).handle(model);
    }
}
