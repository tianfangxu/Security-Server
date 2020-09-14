package com.mot.model;

import com.mot.entity.RoleResourceEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class ParamRoleResourceModel {

    private String id;
    private String roleId;
    private String resourceId;

    private List<ParamRoleResourceModel> add;
    private List<ParamRoleResourceModel> del;

    public void transferEntity(RoleResourceEntity entity, String id) {
        BeanUtils.copyProperties(this,entity);
        entity.createEntity(id);
    }

}
