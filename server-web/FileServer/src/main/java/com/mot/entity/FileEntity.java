package com.mot.entity;

import lombok.Data;

@Data
public class FileEntity extends BaseEntity{

    private String id;
    private String realName;
    private String filePathRoot;
    private String filePathfolder;
    private String filePathName;

}
