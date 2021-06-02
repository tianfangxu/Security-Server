package com.mot.controller;

import com.mot.common.constant.URLConstant;
import com.mot.model.ParamBaseModel;
import com.mot.model.ParamFileModel;
import com.mot.model.ResultBaseModel;
import com.mot.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class FileController {

    @Autowired
    FileService fileService;

    @RequestMapping(value = URLConstant.FILE_URL_01,method = RequestMethod.POST)
    public ResultBaseModel upload(ParamFileModel model){
        return fileService.upload(model);
    }

    @RequestMapping(value = URLConstant.FILE_URL_02,method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response){
        fileService.download(request,response);
    }

    @RequestMapping(value = URLConstant.FILE_URL_03,method = RequestMethod.GET)
    public ResultBaseModel downloadStaticHtml(HttpServletRequest request, HttpServletResponse response){
        //fileService.downloadStaticHtml(request,response);
        return new ResultBaseModel().setResult("23");
    }

}
