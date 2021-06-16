package com.mot.receiver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * @author tianfx
 * @date 2021/6/9 10:47 上午
 */
public interface ReceiverMessageHolder {

    public Boolean receiverMessage(ChannelHandlerContext ctx, WebSocketFrame msg);

}
