package com.mot.controller;

import com.mot.common.constant.URLConstant;
import com.mot.model.ParamLogActionModel;
import com.mot.model.ResultBaseModel;
import com.mot.service.LogActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogActionController {

    @Autowired
    LogActionService logActionService;

    @RequestMapping(value = URLConstant.LOGACTION_URL_01,method = RequestMethod.GET)
    public ResultBaseModel queryLogAction(ParamLogActionModel model){
        try {
            return ResultBaseModel.getSuccess().setResult(logActionService.queryLogAction(model)).setMessage("请求成功！");
        }catch (Exception e){
            e.printStackTrace();
            return ResultBaseModel.getError().setMessage("系统错误，请联系管理员！");
        }
    }
}
