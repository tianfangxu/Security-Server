package com.mot.entity;

import com.mot.constant.CommonConstants;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class BaseEntity implements Serializable {

    private Timestamp createTime;
    private String createUser;
    private Timestamp updateTime;
    private String updateUser;
    private String delflag;

    public BaseEntity createEntity(String createUser){
        this.setCreateTime(new Timestamp(System.currentTimeMillis()));
        this.setCreateUser(createUser);
        this.setDelflag(CommonConstants.DEL_FLAG_0);
        return this;
    }

    public BaseEntity updateEntity(String updateUser){
        this.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        this.setUpdateUser(updateUser);
        this.setDelflag(CommonConstants.DEL_FLAG_0);
        return this;
    }

    public BaseEntity deleteEntity(String updateUser){
        this.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        this.setUpdateUser(updateUser);
        this.setDelflag(CommonConstants.DEL_FLAG_1);
        return this;
    }
}
