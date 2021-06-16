package com.mot.transport;

import com.mot.model.MessageModel;

/**
 * @author tianfx
 * @date 2021/6/9 2:36 下午
 */
public interface TransportHolder {

    void transport(MessageModel model);
}
