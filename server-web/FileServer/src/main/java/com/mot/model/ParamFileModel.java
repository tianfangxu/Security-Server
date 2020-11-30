package com.mot.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ParamFileModel extends ParamBaseModel {

    private List<MultipartFile> files;

}
