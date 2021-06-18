package com.mot.receiver;

import com.mot.common.utils.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.springframework.stereotype.Component;

/**
 * 心跳
 * @author tianfx
 * @date 2021/6/9 10:59 上午
 */
@Component
public class ReceiverMessageBeat implements ReceiverMessageHolder{

    private static String PING = "a";

    @Override
    public Boolean receiverMessage(ChannelHandlerContext ctx, WebSocketFrame msg) {
        if (msg instanceof TextWebSocketFrame){
            String ping = ((TextWebSocketFrame) msg).text();
            if (PING.equals(ping)){
                SessionUtils.resetHeartBeatCount(ctx);
                return false;
            }
        }
        return true;
    }
}
