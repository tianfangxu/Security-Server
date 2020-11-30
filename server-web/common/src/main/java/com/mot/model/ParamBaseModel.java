package com.mot.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ParamBaseModel {

    private String id;
    private int page;
    private int rows;
}
