package com.mot.service;

import com.mot.common.utils.SessionUtils;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class ReceiverByLocalService implements ReceiverService {

    @Override
    public void handleMessage(String msg) {
        //群发
        SessionUtils.getSessions().forEach((k,v)->{
            TextWebSocketFrame text = new TextWebSocketFrame(msg);
            v.writeAndFlush(text);
        });
    }
}
