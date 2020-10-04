package com.arturoeanton.openscimv2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class BulkResponse {

    List<String> schemas = Arrays.asList("urn:ietf:params:scim:api:messages:2.0:BulkResponse");
    @JsonProperty("Operations")
    List<BulkOperationResponse> operations = new ArrayList<>();

}
