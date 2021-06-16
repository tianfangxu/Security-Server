package com.mot.receiver;

import com.alibaba.fastjson.JSON;
import com.mot.model.MessageModel;
import com.mot.transport.TransportLocal;
import com.mot.utils.CommonUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tianfx
 * @date 2021/6/9 1:14 下午
 */
@Component
public class ReceiverMessageContentText implements ReceiverMessageHolder{

    @Autowired
    TransportLocal TransportLocal;

    @Override
    public Boolean receiverMessage(ChannelHandlerContext ctx, WebSocketFrame msg) {
        if (msg instanceof TextWebSocketFrame){
            MessageModel model = JSON.parseObject(((TextWebSocketFrame) msg).text(), MessageModel.class);
            model.setTime(CommonUtil.getCurrentDate());
            TransportLocal.transport(model);
        }
        return false;
    }
}
