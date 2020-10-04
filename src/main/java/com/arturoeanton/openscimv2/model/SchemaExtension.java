package com.arturoeanton.openscimv2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchemaExtension {
    private String schema;
    private boolean required;
}
