package com.mot.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultUserInfoModel implements Serializable {

    private String id;
    private String username;
    private String password;
    private String realName;
    private String avatar;
    private String phone;
    private String email;
    private String sex;
    private String locked;
    private String createTime;
    private String createUser;
    private String updateTime;
    private String updateUser;
    private String delflag;

}
