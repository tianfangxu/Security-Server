package com.mot.send;

import com.alibaba.fastjson.JSON;
import com.mot.handler.message.MessageHandlerFactory;
import com.mot.model.MessageModel;
import org.springframework.stereotype.Component;

/**
 * @author tianfx
 * @date 2021/6/15 11:21 上午
 */
@Component
public class SendHolderLocal implements SendHolder{

    @Override
    public void send(String info) {
        MessageModel model = JSON.parseObject(info, MessageModel.class);
        MessageHandlerFactory.getHolders(model.getType()).handle(model);
    }
}
