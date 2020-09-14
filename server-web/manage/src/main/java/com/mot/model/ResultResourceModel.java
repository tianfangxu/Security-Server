package com.mot.model;

import lombok.Data;

@Data
public class ResultResourceModel {

    private String id;
    private String resourceCode;
    private String resourceName;
    private String resourceUrl;
    private int sort;
    private String parentId;
    private int isLeaf;
    private int type;
    private int state;
}
