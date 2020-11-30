package com.mot.exception;

import lombok.Data;

@Data
public class CustomizedException extends RuntimeException{

    private Integer code;
    private String message;

    public CustomizedException(Integer code,String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
