package com.arturoeanton.openscimv2.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BulkOperationResponse {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    String location= null;
    String method="";
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    String bulkId= null;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    String version=null;
    BulkOperationResponseStatus status = null;

}
