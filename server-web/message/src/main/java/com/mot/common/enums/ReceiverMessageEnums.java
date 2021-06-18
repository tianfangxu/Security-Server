package com.mot.common.enums;

import com.mot.receiver.ReceiverMessageAuth;
import com.mot.receiver.ReceiverMessageBeat;
import com.mot.receiver.ReceiverMessageContentText;
import com.mot.receiver.ReceiverMessageHolder;

/**
 * @author tianfx
 * @date 2021/6/9 10:47 上午
 */
public enum ReceiverMessageEnums {
    鉴权(1, ReceiverMessageAuth.class),
    心跳(0, ReceiverMessageBeat.class),
    文本(9999,ReceiverMessageContentText .class);

    public int code;
    public Class<? extends ReceiverMessageHolder> holder;

    public int getCode() {
        return code;
    }

    public Class<? extends ReceiverMessageHolder> getHolder() {
        return holder;
    }

    ReceiverMessageEnums(int code, Class<? extends ReceiverMessageHolder> holder) {
        this.code = code;
        this.holder = holder;
    }
}
