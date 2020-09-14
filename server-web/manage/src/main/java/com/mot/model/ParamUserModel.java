package com.mot.model;

import com.mot.entity.UserEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ParamUserModel {

    private String id;
    private String username;
    private String password;
    private String realName;
    private String avatar;
    private String phone;
    private String email;
    private Integer sex;
    private Integer locked;
    private List<String> roleIds;

    private int page = 0;
    private int rows = 10;
    private String startTime;
    private String endTime;

    public void transferEntity(UserEntity entity,String userId){
        BeanUtils.copyProperties(this,entity);
        entity.createEntity(userId);
    }

    public boolean isNullOrEmpty(Object val){
        return val == null || "".equals(val) || "null".equals(val) || "undefined".equals(val);
    }

    public static void main(String[] args) {
        System.out.println(new ParamUserModel().isNullOrEmpty(0));
    }
}
