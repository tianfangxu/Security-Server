package com.mot.entity;

import lombok.Data;

@Data
public class DeptEntity extends BaseEntity {

    private String id;
    private String deptCode;
    private String deptName;
    private String parentId;
    private String level;
}
