package com.mot.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResultUserModel implements Serializable {

    private String id;
    private String username;
    private String realName;
    private String avatar;
    private String phone;
    private String email;
    private int sex;
    private int locked;
    private String roleId;
    List<ResultResourceModel> resourceList;

}
