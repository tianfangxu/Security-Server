package com.mot.service.impl;

import com.mot.entity.FileEntity;
import com.mot.handler.file.FileHandler;
import com.mot.model.ParamBaseModel;
import com.mot.model.ParamFileModel;
import com.mot.model.ResultBaseModel;
import com.mot.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileHandler fileHandler;

    @Override
    public ResultBaseModel upload(ParamFileModel model) {
        List<MultipartFile> files = model.getFiles();
        List<FileEntity> filePathList = files.stream().map(file -> fileHandler.save(file)).collect(Collectors.toList());
        return new ResultBaseModel();
    }

    @Override
    public void download(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getParameter("path");
        File file = new File(path);
        if (file.exists()){

        }
    }
}
