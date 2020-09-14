package com.mot.entity;

import lombok.Data;

@Data
public class RoleResourceEntity extends BaseEntity {

    private String id;
    private String roleId;
    private String resourceId;
}
