package com.mot.handler.file;

import com.mot.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileHandler {

    public FileEntity save(File file);
    public FileEntity save(MultipartFile file);
}
