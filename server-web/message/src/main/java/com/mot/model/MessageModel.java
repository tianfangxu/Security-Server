package com.mot.model;

import lombok.Data;

@Data
public class MessageModel {

    /**
     * 消息类型：
     *      可在netty-handler-service-config.properties配置文件中动态添加，不同类型对应不同处理器
     */
    private String type;

    /**
     * 消息主体
     */
    private String body;

    /**
     * 发送人或者其他群体
     */
    private String sendUser;

    /**
     * 接收人或者其他群体
     */
    private String receiverUser;

    /**
     * 发送时间
     */
    private String sendTime;

}
