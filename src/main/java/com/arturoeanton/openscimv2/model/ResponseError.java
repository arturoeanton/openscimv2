package com.arturoeanton.openscimv2.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ResponseError {
    final List<String> schemas = Arrays.asList("urn:ietf:params:scim:api:messages:2.0:Error");
    Integer status = null;
    String detail = "";

    public ResponseError(HttpStatus status, String detail){
        this.status = status.value();
        this.detail = detail;
    }
}