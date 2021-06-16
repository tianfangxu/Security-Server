package com.mot.model;

import com.mot.common.enums.SessionTypeEnums;

/**
 * @author tianfangxu
 */
public class MessageModel {

    /**发送时间*/
    private String time;
    /**类型 eg.{@link SessionTypeEnums}*/
    private int type;
    /**发送方*/
    private String from;
    private String fromId;
    /**发送方鉴权专用*/
    private String sessionId;
    /**接收方*/
    private String to;
    private String toId;
    /**接收主题*/
    private String body;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
