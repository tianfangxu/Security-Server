package com.mot.receiver;

import com.alibaba.fastjson.JSON;
import com.mot.common.utils.SessionUtils;
import com.mot.model.MessageModel;
import com.mot.utils.JwtTokenUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.springframework.stereotype.Component;

/**
 * des:权限校验
 * @author tianfx
 * @date 2021/6/9 10:54 上午
 */
@Component
public class ReceiverMessageAuth implements ReceiverMessageHolder{

    @Override
    public Boolean receiverMessage(ChannelHandlerContext ctx, WebSocketFrame msg) {
        if (SessionUtils.getAllSession().containsValue(ctx)) {
            return true;
        }
        if (msg instanceof TextWebSocketFrame){
            MessageModel model = JSON.parseObject(((TextWebSocketFrame) msg).text(), MessageModel.class);
            String sessionId = model.getSessionId();
            if (sessionId == null || sessionId.length() == 0) {
                return false;
            }
            SessionUtils.add(model.getFromId(), ctx);
            return true;
//            try {
//                return JwtTokenUtil.validateToken(sessionId,model.getForm());
//            }catch (Exception e){
//                e.printStackTrace();
//                return false;
//            }
        }
        return false;
    }
}
