package com.mot.controller;

import com.mot.common.constant.URLConstant;
import com.mot.model.ParamLoginModel;
import com.mot.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.boot.autoconfigure.web.format.WebConversionService;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author tianfx
 * @date 2021/5/13 10:00 上午
 */
@RestController
public class DemoController {

    DemoService demoService;

    @Autowired
    public void setDemoService(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping(URLConstant.DEMO_URL_01)
    public String demoUpdate(){
        demoService.demoUpdate();
        return "success";
    }


    @GetMapping(URLConstant.DEMO_URL_02)
    public String demoSelect(){
        demoService.demoSelect();
        return "success";
    }

    @GetMapping(URLConstant.DEMO_URL_03)
    public String demoInitbinder(@RequestParam(required = true) String id){
        System.out.println(id);
        return "success";
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1,2,3".split(","));
        System.out.println(list);
    }
}
