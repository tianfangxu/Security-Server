package com.mot.handler;

import com.alibaba.fastjson.JSON;
import com.mot.common.utils.SessionUtils;
import com.mot.factory.send.SendMessageServiceFactory;
import com.mot.model.MessageModel;
import com.mot.service.MessageService;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Sharable
@Component
public class WebsocketMessageHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    @Autowired
    SendMessageServiceFactory sendMessageServiceFactory;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
        if (! (msg instanceof TextWebSocketFrame)) {
            // 不接受文本以外的数据帧类型
            System.out.println("不支持的数据类型");return;
        }
        MessageModel model = JSON.parseObject(((TextWebSocketFrame) msg).text(), MessageModel.class);
        model.setSendTime(new Timestamp(System.currentTimeMillis()).toString());
        SessionUtils.getSessions().put(model.getSendUser(),ctx);
        MessageService service = sendMessageServiceFactory.getInstance(model.getType());
        if (service!=null) {
            service.excutor(model);
        } else {
            System.out.println("未找到处理函数");
        }
    }

//    @Override
//    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        if (evt instanceof IdleStateEvent ){
//            IdleStateEvent event = (IdleStateEvent)evt;
//            switch (event.state()){
//                case READER_IDLE:
//                    System.out.println("读空闲");
//                    break;
//                case WRITER_IDLE:
//                    System.out.println("写空闲");
//                    break;
//                case ALL_IDLE:
//                    System.out.println("读写空闲");
//                    break;
//                default:
//                    System.out.println("-->");
//            }
//            /**
//             * 断开连接
//             */
//            //ctx.channel().close();
//        }else {
//            super.userEventTriggered(ctx,evt);
//        }
//    }
}
