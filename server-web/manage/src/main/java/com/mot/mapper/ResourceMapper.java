package com.mot.mapper;

import com.mot.entity.ResourceEntity;
import com.mot.model.ParamResourceModel;
import com.mot.model.ResultResourceModel;
import com.mot.model.ResultRoleInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper {

    List<ResultResourceModel> queryResouceByRoleId(List<String> list);

    List<ResourceEntity> queryResouce(@Param("model") ParamResourceModel model);

    int queryResouceCount(@Param("model") ParamResourceModel model);

    int insertResource(ResourceEntity entity);
}
