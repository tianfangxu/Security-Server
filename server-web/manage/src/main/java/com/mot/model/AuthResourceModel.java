package com.mot.model;

import lombok.Data;

import java.util.List;

@Data
public class AuthResourceModel {

    private String id;
    private String resourceCode;
    private String resourceName;
    List<AuthResourceModel> childs;
}
