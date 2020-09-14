package com.mot.model;

import lombok.Data;

@Data
public class ResultRoleInfoModel {

    private String id;
    private String roleCode;
    private String roleName;
    private String description;
    private String createTime;
    private String createUser;
    private String updateTime;
    private String updateUser;
    private String delflag;
}
