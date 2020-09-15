package com.mot.entity;

import lombok.Data;

@Data
public class ResourceEntity extends BaseEntity {
    private String id;
    private String resourceCode;
    /**
     * 字段命名规则：系统名称.操作对象.功能名称 （描述越清晰越好）
     */
    private String resourceName;
    private String resourceUrl;
    private int sort;
    private String parentId;
    private int isLeaf;
    private int type;
    /**
     * 暂时用不上的字段
     */
    private int state;
}
