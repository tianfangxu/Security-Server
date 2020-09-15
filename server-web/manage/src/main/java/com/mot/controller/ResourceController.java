package com.mot.controller;

import com.mot.common.constant.URLConstant;
import com.mot.model.ParamResourceModel;
import com.mot.model.ResultBaseModel;
import com.mot.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    @RequestMapping(value = URLConstant.RESOURCE_URL_01,method = RequestMethod.GET)
    public ResultBaseModel queryResource(ParamResourceModel model){
        try {
            return ResultBaseModel.getSuccess().setResult(resourceService.queryResource(model)).setMessage("请求成功！");
        }catch (Exception e){
            e.printStackTrace();
            return ResultBaseModel.getError().setMessage("系统错误，请联系管理员！");
        }
    }

    @RequestMapping(value = URLConstant.RESOURCE_URL_02,method = RequestMethod.POST)
    public ResultBaseModel insertResource(@RequestBody ParamResourceModel model){
        try {
            return resourceService.insertResource(model);
        }catch (Exception e){
            e.printStackTrace();
            return ResultBaseModel.getError().setMessage("系统错误，请联系管理员！");
        }
    }

    @RequestMapping(value = URLConstant.RESOURCE_URL_03,method = RequestMethod.POST)
    public ResultBaseModel deleteResource(@RequestBody ParamResourceModel model){
        try {
            return resourceService.deleteResource(model);
        }catch (Exception e){
            e.printStackTrace();
            return ResultBaseModel.getError().setMessage("系统错误，请联系管理员！");
        }
    }
}
