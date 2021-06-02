package com.mot.controller;

import com.mot.common.constant.URLConstant;
import com.mot.model.ParamLoginModel;
import com.mot.model.ParamRefreshTokenModel;
import com.mot.model.ResultBaseModel;
import com.mot.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginUserService loginUserService;

    @RequestMapping(value = URLConstant.LOGIN_URL_01 ,method = RequestMethod.POST)
    public ResultBaseModel login(@RequestBody ParamLoginModel model){
        try {
            return loginUserService.login(model.getUsername(),model.getPassword());
        }catch (Exception e){
            e.printStackTrace();
            return ResultBaseModel.getError().setMessage("系统错误，请联系管理员！");
        }

    }

    @RequestMapping(value = URLConstant.LOGIN_URL_02 ,method = RequestMethod.GET)
    public ResultBaseModel refreshToken(ParamRefreshTokenModel model){
        try {
            return loginUserService.refreshToken(model.getToken());
        }catch (Exception e){
            return ResultBaseModel.getError().setMessage("系统错误，请联系管理员！");
        }

    }
}
