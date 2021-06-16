package com.mot.handler.message;

import com.mot.model.MessageModel;

/**
 * des:消息处理接口类
 * @author tianfx
 * @date 2021/6/15 2:15 下午
 */
public interface MessageHandlerHolder {

    void handle(MessageModel model);
}
