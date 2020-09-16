package com.mot.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class LogActionEntity {

    private String id;
    private String server;
    private String url;
    private String request;
    private String response;
    private Timestamp createTime;
    private String createUserId;
    private String createUserName;
    private String createUserIp;
    private String createUserClient;
}
