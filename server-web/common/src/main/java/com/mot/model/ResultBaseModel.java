package com.mot.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResultBaseModel {

    private int state;
    private String message;
    private Object result;
}
