package com.arturoeanton.openscimv2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResourceType {

    List<String> schemas = new ArrayList<>();
    List<SchemaExtension> schemaExtensions = new ArrayList<>();
    Meta meta = new Meta();
    @JsonIgnoreProperties
    private String id = "";
    @JsonIgnoreProperties
    private String name = "";
    @JsonIgnoreProperties
    private String endpoint = "";
    @JsonIgnoreProperties
    private String description = "";
    @JsonIgnoreProperties
    private String schema = "";
}
