package com.mot.common.enums;

import com.mot.handler.message.MessageHandlerChatroom;
import com.mot.handler.message.MessageHandlerHolder;

/**
 * @author tianfx
 * @date 2021/6/3 4:36 下午
 */
public enum SessionTypeEnums {

    聊天室(1, MessageHandlerChatroom.class),
    单聊(2,null),
    群聊(3,null),
    系统通知(4,null),
    公众号(5,null);

    public int code;
    public Class<? extends MessageHandlerHolder> handler;

    SessionTypeEnums(int code,Class<? extends MessageHandlerHolder> handler) {
        this.code = code;
        this.handler = handler;
    }
}
