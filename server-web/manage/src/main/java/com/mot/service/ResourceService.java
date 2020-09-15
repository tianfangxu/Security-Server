package com.mot.service;

import com.mot.model.ParamResourceModel;
import com.mot.model.ResultBaseModel;

import java.util.Map;

public interface ResourceService {
    Map queryResource(ParamResourceModel model);

    ResultBaseModel insertResource(ParamResourceModel model);

    ResultBaseModel deleteResource(ParamResourceModel model);
}
