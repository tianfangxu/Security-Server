package com.mot.service.impl;

import com.mot.common.utils.ModelUtils;
import com.mot.entity.LogActionEntity;
import com.mot.mapper.LogActionMapper;
import com.mot.model.ParamLogActionModel;
import com.mot.service.LogActionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogActionServiceImpl implements LogActionService {

    @Autowired
    LogActionMapper logActionMapper;

    @Override
    public int insertLog(ParamLogActionModel model) {
        LogActionEntity entity = new LogActionEntity();
        BeanUtils.copyProperties(model,entity);
        entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return logActionMapper.insertLog(entity);
    }

    @Override
    public Map queryLogAction(ParamLogActionModel model) {
        Map map = new HashMap<>();
        ModelUtils.clearModel(model);
        model.setPage((model.getPage() -1) * model.getRows());
        List<LogActionEntity> list = logActionMapper.queryLogAction(model);
        int count = logActionMapper.queryLogActionCount(model);
        map.put("total",count);
        map.put("rows",list);
        return map;
    }
}
