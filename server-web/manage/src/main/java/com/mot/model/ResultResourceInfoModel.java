package com.mot.model;

import lombok.Data;

@Data
public class ResultResourceInfoModel {

    private String id;
    private String resourceCode;
    private String resourceName;
    private String resourceUrl;
    private String parentId;
    private String sort;
    private String isLeaf;
    private String type;
    private String state;

    private String createTime;
    private String createUser;
    private String updateTime;
    private String updateUser;
    private String delflag;

}
