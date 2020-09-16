package com.mot.service;

import com.mot.model.ParamLogActionModel;

import java.util.Map;

public interface LogActionService {

    int insertLog(ParamLogActionModel model);

    Map queryLogAction(ParamLogActionModel model);
}
