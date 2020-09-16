package com.mot.mapper;

import com.mot.entity.LogActionEntity;
import com.mot.model.ParamLogActionModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogActionMapper {
    int insertLog(@Param("entity") LogActionEntity entity);

    List<LogActionEntity> queryLogAction(@Param("model") ParamLogActionModel model);

    int queryLogActionCount(@Param("model") ParamLogActionModel model);
}
