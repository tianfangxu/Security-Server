package com.mot.entity;

import lombok.Data;

@Data
public class RoleEntity extends BaseEntity {
    private String id;
    private String roleCode;
    private String roleName;
    private String description;
}
