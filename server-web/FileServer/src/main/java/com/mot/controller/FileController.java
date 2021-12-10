package com.mot.controller;

import com.mot.common.constant.URLConstant;
import com.mot.config.properties.GlobalSettingConfig;
import com.mot.model.ParamBaseModel;
import com.mot.model.ParamFileModel;
import com.mot.model.ResultBaseModel;
import com.mot.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class FileController {

    @Autowired
    FileService fileService;

    @Autowired
    GlobalSettingConfig globalSettingConfig;

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

    @RequestMapping(value = URLConstant.FILE_URL_04,method = RequestMethod.POST)
    public void excelView(MultipartFile file,HttpServletResponse response){
        fileService.excelView(file, response);
    }
    @RequestMapping(value = URLConstant.FILE_URL_05,method = RequestMethod.GET)
    public void excelView(String path,HttpServletResponse response) throws IOException {
        String s = globalSettingConfig.rootFilePath + "/" + path + ".html";
        ServletOutputStream outputStream = response.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(new File(s));
        byte[] b = new byte[8096];
        while (fileInputStream.read(b) > 0) {
            outputStream.write(b);
        }
        fileInputStream.close();
        outputStream.close();
    }

}
