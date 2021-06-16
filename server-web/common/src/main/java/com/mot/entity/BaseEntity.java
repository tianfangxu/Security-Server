package com.mot.entity;

import com.mot.constant.CommonConstants;

import java.io.Serializable;
import java.sql.Timestamp;

public class BaseEntity implements Serializable {

    private Timestamp createTime;
    private String createUser;
    private Timestamp updateTime;
    private String updateUser;
    private String delflag;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

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
