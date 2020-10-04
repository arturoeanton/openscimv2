package com.arturoeanton.openscimv2.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Operation {

    private String op="";
    private String path="";
    private Object value =null ;



}
