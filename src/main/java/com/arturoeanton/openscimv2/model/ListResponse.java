package com.arturoeanton.openscimv2.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ListResponse {
    List< String > schemas = List.of("urn:ietf:params:scim:api:messages:2.0:ListResponse");

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer itemsPerPage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer startIndex;

    private Long totalResults;

    @JsonProperty("Resources")
    List <Map<String,Object>>resources = new ArrayList<>();

    private ListResponse(){}

    public ListResponse( List <Map<String,Object>> elem, Long totalResults ) {
        resources = elem;
        this.totalResults = totalResults;
    }







}
