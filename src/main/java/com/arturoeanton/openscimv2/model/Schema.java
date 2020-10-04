package com.arturoeanton.openscimv2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Schema {
    private String id;
    private String name;
    private String description;
    List<Attribute> attributes = new ArrayList<>();
    @JsonIgnoreProperties
    Meta meta;
}

