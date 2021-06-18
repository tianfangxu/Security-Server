package com.mot.job;

import com.mot.common.enums.SessionTypeEnums;
import com.mot.common.utils.SessionUtils;
import com.mot.common.utils.SpringContextHolder;
import com.mot.handler.message.MessageHandlerChatroom;
import com.mot.model.MessageModel;
import com.mot.utils.CommonUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author tianfx
 * @date 2021/6/18 9:49 上午
 */
@Component
public class ChannelJob {

    @Scheduled(cron = "0/5 * * * * ?")
    public void SessionTimer(){
        Map<String, SessionUtils.SessionInfo> allSession = SessionUtils.getAllSession();
        for (String k : allSession.keySet()) {
            SessionUtils.SessionInfo v = allSession.get(k);
            if (!v.channelHandlerContext.channel().isActive()){
                System.out.println(String.format("id:%s,name:%s is destroyed", k,v.fromName));
                SessionUtils.getHeartBeat().remove(v);
                allSession.remove(k);
                MessageModel model = new MessageModel();
                model.setTime(CommonUtil.getCurrentDate());
                model.setFrom("system");
                model.setFromId("system");
                model.setType(SessionTypeEnums.系统通知.code);
                model.setBody("<div style='color: #ff3262;font-size: 8px;text-align: center'>系统通知：[ "+v.fromName+" ]已离线！</div>");
                SpringContextHolder.getApplicationContext().getBean(MessageHandlerChatroom.class).handle(model);
                v.channelHandlerContext.close();
            }
        }
    }

}
