package com.mot.service;

import com.mot.model.ParamBaseModel;
import com.mot.model.ParamFileModel;
import com.mot.model.ResultBaseModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileService {
    ResultBaseModel upload(ParamFileModel model);

    void download(HttpServletRequest request, HttpServletResponse response);

    String downloadStaticHtml(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void excelView(MultipartFile file,HttpServletResponse response);
}
