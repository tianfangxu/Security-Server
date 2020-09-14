package com.mot.entity;

import lombok.Data;

@Data
public class UserEntity extends BaseEntity{

    private String id;
    private String username;
    private String password;
    private String realName;
    private String avatar;
    private String phone;
    private String email;
    private int sex;
    private int locked;
}
