package com.arturoeanton.openscimv2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Meta {
    @JsonIgnoreProperties
    private String resourceType;
    @JsonIgnoreProperties
    private String location;
}