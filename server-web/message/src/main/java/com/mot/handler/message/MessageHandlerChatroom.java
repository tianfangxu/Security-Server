package com.mot.handler.message;

import com.alibaba.fastjson.JSON;
import com.mot.common.utils.SessionUtils;
import com.mot.model.MessageModel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Component;

/**
 * des:聊天室
 * @author tianfx
 * @date 2021/6/15 2:30 下午
 */
@Component
public class MessageHandlerChatroom implements MessageHandlerHolder{
    @Override
    public void handle(MessageModel model) {
        TextWebSocketFrame frame = new TextWebSocketFrame(JSON.toJSONString(model));
        SessionUtils.getAllSession().forEach((k,y)->y.writeAndFlush(frame));
    }
}
