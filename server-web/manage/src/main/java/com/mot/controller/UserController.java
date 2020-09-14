package com.mot.controller;

import com.mot.common.constant.URLConstant;
import com.mot.entity.UserEntity;
import com.mot.model.ParamUserModel;
import com.mot.model.ResultBaseModel;
import com.mot.model.ResultUserInfoModel;
import com.mot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 查询用户基本信息
     * 时间字段传值格式：yyyy-mm-dd hh:mm:ss[.fffffffff]
     * @return
     */
    @RequestMapping(value = URLConstant.USER_URL_01,method = RequestMethod.GET)
    public ResultBaseModel queryUser(ParamUserModel model){
        try {
            return ResultBaseModel.getSuccess().setResult(userService.queryUser(model)).setMessage("请求成功！");
        }catch (Exception e){
            e.printStackTrace();
            return ResultBaseModel.getError().setMessage("系统错误，请联系管理员！");
        }
    }
    @RequestMapping(value = URLConstant.USER_URL_02,method = RequestMethod.POST)
    public ResultBaseModel insertUser(@RequestBody ParamUserModel model){
        try {
            return userService.insertUser(model);
        }catch (Exception e){
            e.printStackTrace();
            return ResultBaseModel.getError().setMessage("系统错误，请联系管理员！");
        }
    }
    @RequestMapping(value = URLConstant.USER_URL_03,method = RequestMethod.POST)
    public ResultBaseModel updateUser(@RequestBody ParamUserModel model){
        try {
            return userService.updateUser(model);
        }catch (Exception e){
            e.printStackTrace();
            return ResultBaseModel.getError().setMessage("系统错误，请联系管理员！");
        }
    }
    @RequestMapping(value = URLConstant.USER_URL_04,method = RequestMethod.POST)
    public ResultBaseModel deleteUser(@RequestBody ParamUserModel model){
        return ResultBaseModel.getSuccess();
    }
    @RequestMapping(value = URLConstant.USER_URL_05,method = RequestMethod.POST)
    public ResultBaseModel batchInsertUser(@RequestBody List<ParamUserModel> list){
        return ResultBaseModel.getSuccess();
    }
    @RequestMapping(value = URLConstant.USER_URL_06,method = RequestMethod.POST)
    public ResultBaseModel batchUpdateUser(@RequestBody List<ParamUserModel> list){
        return ResultBaseModel.getSuccess();
    }
    @RequestMapping(value = URLConstant.USER_URL_07,method = RequestMethod.POST)
    public ResultBaseModel batchDeleteUser(@RequestBody List<ParamUserModel> list){
        return ResultBaseModel.getSuccess();
    }
    @RequestMapping(value = URLConstant.USER_URL_08,method = RequestMethod.POST)
    public ResultBaseModel resetPassword(@RequestBody ParamUserModel model){
        try {
            return userService.resetPassword(model);
        }catch (Exception e){
            e.printStackTrace();
            return ResultBaseModel.getError().setMessage("系统错误，请联系管理员！");
        }
    }

}
