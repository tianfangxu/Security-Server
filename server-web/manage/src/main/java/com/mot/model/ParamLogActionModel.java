package com.mot.model;

import lombok.Data;

@Data
public class ParamLogActionModel {

    private String id;
    private String server;
    private String url;
    private String request;
    private String response;
    private String createUserId;
    private String createUserName;
    private String createUserIp;
    private String createUserClient;

    private int page = 0;
    private int rows = 10;
    private String startTime;
    private String endTime;
}
