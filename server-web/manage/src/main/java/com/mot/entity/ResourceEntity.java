package com.mot.entity;

import lombok.Data;

@Data
public class ResourceEntity extends BaseEntity {
    private String id;
    private String resourceCode;
    private String resourceName;
    private String resourceUrl;
    private int sort;
    private String parentId;
    private int isLeaf;
    private int type;
    private int state;  //暂时用不上的字段
}
