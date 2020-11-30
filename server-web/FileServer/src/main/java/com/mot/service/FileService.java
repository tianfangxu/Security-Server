package com.mot.service;

import com.mot.model.ParamBaseModel;
import com.mot.model.ParamFileModel;
import com.mot.model.ResultBaseModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileService {
    ResultBaseModel upload(ParamFileModel model);

    void download(HttpServletRequest request, HttpServletResponse response);
}
