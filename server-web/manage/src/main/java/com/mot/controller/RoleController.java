package com.mot.controller;

import com.mot.common.constant.URLConstant;
import com.mot.model.ParamRoleModel;
import com.mot.model.ParamRoleResourceModel;
import com.mot.model.ParamUserModel;
import com.mot.model.ResultBaseModel;
import com.mot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping(value = URLConstant.ROLE_URL_01,method = RequestMethod.GET)
    public ResultBaseModel queryRole(ParamRoleModel model){
        try {
            return ResultBaseModel.getSuccess().setResult(roleService.queryRole(model)).setMessage("请求成功！");
        }catch (Exception e){
            e.printStackTrace();
            return ResultBaseModel.getError().setMessage("系统错误，请联系管理员！");
        }
    }

    @RequestMapping(value = URLConstant.ROLE_URL_02,method = RequestMethod.POST)
    public ResultBaseModel insertRole(@RequestBody ParamRoleModel model){
        try {
            return roleService.insertRole(model);
        }catch (Exception e){
            e.printStackTrace();
            return ResultBaseModel.getError().setMessage("系统错误，请联系管理员！");
        }
    }

    @RequestMapping(value = URLConstant.ROLE_URL_03,method = RequestMethod.GET)
    public ResultBaseModel queryRoleResource(ParamRoleModel model){
        try {
            return ResultBaseModel.getSuccess().setResult(roleService.queryRoleResource(model)).setMessage("请求成功！");
        }catch (Exception e){
            e.printStackTrace();
            return ResultBaseModel.getError().setMessage("系统错误，请联系管理员！");
        }
    }

    @RequestMapping(value = URLConstant.ROLE_URL_04,method = RequestMethod.POST)
    public ResultBaseModel batchInsertRoleResource(@RequestBody List<ParamRoleResourceModel> list){
        try {
            return roleService.batchInsertRoleResource(list);
        }catch (Exception e){
            e.printStackTrace();
            return ResultBaseModel.getError().setMessage("系统错误，请联系管理员！");
        }
    }

    @RequestMapping(value = URLConstant.ROLE_URL_05,method = RequestMethod.POST)
    public ResultBaseModel batchDeleteRoleResource(@RequestBody List<ParamRoleResourceModel> list){
        try {
            return roleService.batchDeleteRoleResource(list);
        }catch (Exception e){
            e.printStackTrace();
            return ResultBaseModel.getError().setMessage("系统错误，请联系管理员！");
        }
    }

    @RequestMapping(value = URLConstant.ROLE_URL_06,method = RequestMethod.POST)
    public ResultBaseModel batchUpdateRoleResource(@RequestBody ParamRoleResourceModel list){
        try {
            return roleService.batchUpdateRoleResource(list);
        }catch (Exception e){
            e.printStackTrace();
            return ResultBaseModel.getError().setMessage("系统错误，请联系管理员！");
        }
    }

    @RequestMapping(value = URLConstant.ROLE_URL_07,method = RequestMethod.GET)
    public ResultBaseModel queryRoleByUserId(ParamUserModel model){
        try {
            return ResultBaseModel.getSuccess().setResult(roleService.queryRoleByUserId(model)).setMessage("请求成功！");
        }catch (Exception e){
            e.printStackTrace();
            return ResultBaseModel.getError().setMessage("系统错误，请联系管理员！");
        }
    }


}
