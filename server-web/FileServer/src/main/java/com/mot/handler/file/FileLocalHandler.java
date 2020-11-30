package com.mot.handler.file;

import com.mot.config.properties.GlobalSettingConfig;
import com.mot.entity.FileEntity;
import com.mot.utils.FileUtils;
import com.mot.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@Primary
public class FileLocalHandler implements FileHandler{

    @Autowired
    GlobalSettingConfig globalSettingConfig;

    @Override
    public FileEntity save(File file) {
        return null;
    }

    @Override
    public FileEntity save(MultipartFile file) {
        String fileName = null;
        try {
            fileName = MD5Util.getFileMD5(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String folder = new StringBuilder(fileName.substring(0,2)).append("/")
                .append(fileName.substring(2,4)).append("/")
                .append(fileName.substring(4,6)).append("/")
                .append(fileName.substring(6,8)).append("/")
                .append(fileName.substring(8,10)).append("/").toString();
        String filePath = globalSettingConfig.rootFilePath + folder + fileName;
        File newFile = new File(filePath);
        if (!newFile.exists()){
            //文件不存在，则本地存储
            try {
                FileUtils.createNewFile(newFile);
                file.transferTo(newFile);
            } catch (IOException e) {

            }
        }
        FileEntity fileEntity = new FileEntity();
        fileEntity.setRealName(file.getName());
        fileEntity.setFilePathRoot(globalSettingConfig.rootFilePath);
        fileEntity.setFilePathfolder(folder);
        fileEntity.setFilePathName(fileName);
        return fileEntity;
    }
}
