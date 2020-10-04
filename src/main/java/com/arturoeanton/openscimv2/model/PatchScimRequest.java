package com.arturoeanton.openscimv2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ToString
public class PatchScimRequest {
    private List<String> schemas = Arrays.asList("urn:ietf:params:scim:api:messages:2.0:PatchOp");
    @JsonProperty("Operations")
    private List<Operation> operations = new ArrayList<>();
}
