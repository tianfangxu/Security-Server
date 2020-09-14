package com.mot.entity;

import com.mot.common.constant.CommonConstant;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class BaseEntity implements Serializable{

    private Timestamp createTime;
    private String createUser;
    private Timestamp updateTime;
    private String updateUser;
    private int delflag;

    public void createEntity(String userId){
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.createUser = userId;
        this.delflag = CommonConstant.BASE_DATA_FLAG_EXIT;
    }

    public void updateEntiy(String userId){
        this.updateTime = new Timestamp(System.currentTimeMillis());
        this.updateUser = userId;
        this.delflag = CommonConstant.BASE_DATA_FLAG_EXIT;
    }

    public void deleteEntiy(String userId){
        this.updateTime = new Timestamp(System.currentTimeMillis());
        this.updateUser = userId;
        this.delflag = CommonConstant.BASE_DATA_FLAG_NO_EXIT;
    }
}
