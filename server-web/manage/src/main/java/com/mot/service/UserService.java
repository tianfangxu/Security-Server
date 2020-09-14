package com.mot.service;

import com.mot.model.ParamUserModel;
import com.mot.model.ResultBaseModel;

import java.util.Map;

public interface UserService {
    Map queryUser(ParamUserModel model);

    ResultBaseModel insertUser(ParamUserModel model);

    ResultBaseModel updateUser(ParamUserModel model);

    ResultBaseModel resetPassword(ParamUserModel model);
}
