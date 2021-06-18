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

    static Map<String, SessionInfo> sessions = new HashMap();
    static Map<ChannelHandlerContext, Integer> heartBeat = new HashMap();

    public static Map<String, SessionInfo> getSessions() {
        return sessions;
    }

    public static Map<ChannelHandlerContext, Integer> getHeartBeat() {
        return heartBeat;
    }

    private static Integer maxCount = 5;

    public static void add(String sessionId,String fromName,ChannelHandlerContext channel){
        SessionInfo oldchannel = sessions.put(sessionId, new SessionInfo(channel,fromName));
        if (oldchannel != null && channel != oldchannel.channelHandlerContext){
            oldchannel.channelHandlerContext.close();
        }
        heartBeat.remove(channel);
    }

    public static SessionInfo getSession(String sessionId){
        SessionInfo channel = sessions.get(sessionId);
        if (channel == null || heartBeat.get(channel) >= maxCount) {
            heartBeat.remove(channel);
            return null;
        }
        return channel;
    }

    public static Map<String, SessionInfo> getAllSession(){
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
                if (sessions.get(k).channelHandlerContext == channel){
                    sessions.remove(k);
                    fromId = k;
                    break;
                }
            }
            String fromName = getSession(fromId).fromName;
            MessageModel model = new MessageModel();
            model.setTime(CommonUtil.getCurrentDate());
            model.setFrom("system");
            model.setFromId("system");
            model.setType(SessionTypeEnums.系统通知.code);
            model.setBody("<div style='color: #ff3262;font-size: 8px;text-align: center'>系统通知["+fromName+"]已离线！</div>");
            SpringContextHolder.getApplicationContext().getBean(MessageHandlerChatroom.class).handle(model);
            channel.close();
        }
    }

    public static class SessionInfo{
        public ChannelHandlerContext channelHandlerContext;
        public String fromName;

        public SessionInfo(ChannelHandlerContext channelHandlerContext, String fromName) {
            this.channelHandlerContext = channelHandlerContext;
            this.fromName = fromName;
        }
    }
}
