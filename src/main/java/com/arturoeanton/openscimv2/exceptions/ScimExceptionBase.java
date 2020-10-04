package com.arturoeanton.openscimv2.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScimExceptionBase extends Exception {


    private Integer code;
    private String message;

    public ScimExceptionBase(String message, Integer code) {
        this.message = message;
        this.code = code;
    }


}
