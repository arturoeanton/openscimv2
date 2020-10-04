package com.arturoeanton.openscimv2.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Search {

    List< String > schemas = Arrays.asList("urn:ietf:params:scim:api:messages:2.0:SearchRequest");
    List < String > attributes = new ArrayList<>();
    List < String > excludedAttributes = new ArrayList<>();
    String sortBy = "";
    String sortOrder = "ascending";
    Integer startIndex = 1;
    Integer count = 0 ;
    private String filter;

}
