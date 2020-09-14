package com.mot.entity;

import lombok.Data;

@Data
public class UserRoleEntity extends BaseEntity{

    private String id;
    private String roleId;
    private String userId;

}
