package com.mot.model;

import com.mot.entity.RoleEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;

@Data
public class ParamRoleModel {

    private String id;
    private String roleCode;
    private String roleName;
    private String description;


    private int page = 0;
    private int rows = 10;
    private Timestamp startTime;
    private Timestamp endTime;

    public void transferEntity(RoleEntity roleEntity, String id) {
        BeanUtils.copyProperties(this,roleEntity);
        roleEntity.createEntity(id);
    }
}
