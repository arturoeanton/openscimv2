package com.arturoeanton.openscimv2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class BulkOperation {

    String method="";
    String path="";
    String bulkId= "";
    String version="";
    Map<String,Object> data = null;


    @JsonIgnore
    Object response = null;

}
