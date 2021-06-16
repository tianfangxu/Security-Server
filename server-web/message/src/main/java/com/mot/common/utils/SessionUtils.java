package com.mot.common.utils;

import com.mot.common.enums.SessionTypeEnums;
import com.mot.handler.message.MessageHandlerChatroom;
import com.mot.model.MessageModel;
import com.mot.utils.CommonUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tianfx
 * @date 2021/6/1 1:58 下午
 */
public class SessionUtils {

    static Map<String, ChannelHandlerContext> sessions = new HashMap();
    static Map<ChannelHandlerContext, Integer> heartBeat = new HashMap();

    private static Integer maxCount = 5;

    public static void add(String sessionId,ChannelHandlerContext channel){
        ChannelHandlerContext oldchannel = sessions.put(sessionId, channel);
        if (oldchannel != null && channel != oldchannel){
            oldchannel.close();
        }
        heartBeat.remove(channel);
    }

    public static ChannelHandlerContext getSession(String sessionId){
        ChannelHandlerContext channel = sessions.get(sessionId);
        if (channel == null || heartBeat.get(channel) >= maxCount) {
            heartBeat.remove(channel);
            return null;
        }
        return channel;
    }

    public static Map<String, ChannelHandlerContext> getAllSession(){
        return sessions;
    }

    public static void resetHeartBeatCount(ChannelHandlerContext channel){
        heartBeat.remove(channel);
    }

    public static void addHeartBeatCount(ChannelHandlerContext channel){
        Integer integer = heartBeat.get(channel);
        if (integer == null){
            integer = new Integer(1);
            heartBeat.put(channel,integer);
        }else if (integer.intValue() < 6){
            heartBeat.put(channel,integer+1);
        }else {
            heartBeat.remove(channel);
            String fromId = null;
            for (String k : sessions.keySet()) {
                if (sessions.get(k) == channel){
                    sessions.remove(k);
                    fromId = k;
                    break;
                }
            }
            MessageModel model = new MessageModel();
            model.setTime(CommonUtil.getCurrentDate());
            model.setFrom("system");
            model.setFromId("system");
            model.setType(SessionTypeEnums.系统通知.code);
            model.setBody("["+fromId+"]已离线！");
            SpringContextHolder.getApplicationContext().getBean(MessageHandlerChatroom.class).handle(model);
            channel.close();
        }
    }
}
