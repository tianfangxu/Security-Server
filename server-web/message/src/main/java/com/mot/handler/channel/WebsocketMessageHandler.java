package com.mot.handler.channel;

import com.mot.common.utils.SessionUtils;
import com.mot.receiver.ReceiverMessageFactory;
import com.mot.receiver.ReceiverMessageHolder;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.stereotype.Component;

/**
 * @author tianfangxu
 */
@Sharable
@Component
public class WebsocketMessageHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) {
        for (ReceiverMessageHolder holder : ReceiverMessageFactory.getHolders()) {
            if (holder != null && !holder.receiverMessage(ctx,msg)) {
                break;
            }
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent && ((IdleStateEvent)evt).state().equals(IdleState.ALL_IDLE)){
            SessionUtils.addHeartBeatCount(ctx);
        }else {
            super.userEventTriggered(ctx,evt);
        }
    }
}
