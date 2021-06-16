package com.mot.transport;

import com.alibaba.fastjson.JSON;
import com.mot.model.MessageModel;
import com.mot.send.SendHolderLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tianfx
 * @date 2021/6/9 2:37 下午
 */
@Component
public class TransportLocal implements TransportHolder{
    @Autowired
    SendHolderLocal sendHolderLocal;

    @Override
    public void transport(MessageModel model) {
        String info = JSON.toJSONString(model);
        sendHolderLocal.send(info);
    }
}
