package com.mot.model;

import com.mot.common.utils.ModelUtils;
import com.mot.entity.ResourceEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;

@Data
public class ParamResourceModel {

    private String id;
    private String resourceCode;
    private String resourceName;
    private String resourceUrl;
    private int sort;
    private String parentId;
    private int isLeaf;
    private String type;
    private int state;

    private int page = 0;
    private int rows = 10;
    private Timestamp startTime;
    private Timestamp endTime;

    public void transferEntity(ResourceEntity entity, String userId) {
        BeanUtils.copyProperties(this,entity);
        entity.setType(ModelUtils.isNullOrEmpty(this.type)?1:Integer.parseInt(this.type));
        entity.createEntity(userId);
    }
}
