package com.mot.service;

import com.mot.model.ParamRoleModel;
import com.mot.model.ParamRoleResourceModel;
import com.mot.model.ParamUserModel;
import com.mot.model.ResultBaseModel;

import java.util.List;
import java.util.Map;

public interface RoleService {
    Map queryRole(ParamRoleModel model);

    ResultBaseModel insertRole(ParamRoleModel model);

    Map queryRoleResource(ParamRoleModel model);

    ResultBaseModel batchInsertRoleResource(List<ParamRoleResourceModel> list);

    ResultBaseModel batchDeleteRoleResource(List<ParamRoleResourceModel> list);

    ResultBaseModel batchUpdateRoleResource(ParamRoleResourceModel list);

    Map queryRoleByUserId(ParamUserModel model);
}
