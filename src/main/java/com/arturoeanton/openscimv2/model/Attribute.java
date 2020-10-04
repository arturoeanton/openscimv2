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
public class Attribute {

    private String name;
    @JsonIgnoreProperties
    private String type = "string";

    @JsonIgnoreProperties
    private String _formatDate = "MM/dd/yyyy HH:mm:ss";

    @JsonIgnoreProperties
    private boolean _encrypted = false;


    @JsonIgnoreProperties
    private boolean multiValued = false;
    @JsonIgnoreProperties
    private String description = "";
    @JsonIgnoreProperties
    private boolean required = false;
    @JsonIgnoreProperties
    private boolean caseExact = false;
    @JsonIgnoreProperties
    private String mutability = "readWrite";
    @JsonIgnoreProperties
    private String returned = "default";

    @JsonIgnoreProperties
    private String uniqueness = "none";

    @JsonIgnoreProperties
    private List<String> referenceTypes = new ArrayList<>();

    @JsonIgnoreProperties
    private List<String> canonicalValues = new ArrayList<>();

    @JsonIgnoreProperties
    private List<Attribute> subAttributes = new ArrayList<>();


}
