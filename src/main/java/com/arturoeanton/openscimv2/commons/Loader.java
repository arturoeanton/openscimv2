package com.arturoeanton.openscimv2.commons;

import com.arturoeanton.openscimv2.components.Schemas;
import com.arturoeanton.openscimv2.model.Attribute;
import com.arturoeanton.openscimv2.model.ResourceType;
import com.arturoeanton.openscimv2.model.Schema;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class Loader {
    public static void loadAttributes(String folderConfig, Schemas schemas) throws IOException {
        var files = Files.list(Paths.get(
                folderConfig,
                Constants.DIRECTORY_RESOURCE_TYPE)).filter(x -> !x.endsWith("json")).collect(Collectors.toList());
        ObjectMapper mapper = new ObjectMapper();
        for (var f : files) {

            var resourceType = mapper.readValue(f.toFile(), ResourceType.class);
            var pathSchema = Paths.get(folderConfig + File.separator
                    + Constants.DIRECTORY_SCHEMA_TYPE + File.separator + resourceType.getSchema() + ".json");
            var schema = mapper.readValue(pathSchema.toFile(), Schema.class);

            schemas.urnToExtensions.put(resourceType.getSchema(), resourceType.getSchemaExtensions());
            schemas.endpointToSchema.put(resourceType.getEndpoint(), schema);
            schemas.urnToSchema.put(schema.getId(), schema);
            loadAttributes(schemas,schema.getId() ,schema.getAttributes(), "");
            log.info(schema.getId());

            for (var extension : resourceType.getSchemaExtensions()) {
                var pathSchemaExtension = Paths.get(folderConfig + File.separator
                        + Constants.DIRECTORY_SCHEMA_TYPE + File.separator + extension.getSchema() + ".json");
                var schemaExtension = mapper.readValue(pathSchemaExtension.toFile(), Schema.class);
                schemas.urnToSchema.put(schemaExtension.getId(), schemaExtension);
                loadAttributes(schemas,schemaExtension.getId() ,schemaExtension.getAttributes(), "");
                log.info(schemaExtension.getId());
            }
        }
    }

    private static void loadAttributes(Schemas schemas, String urn, List<Attribute> attributes, String oldPath) {
        for (var attribute : attributes){
            var path = oldPath+("".equals(oldPath)?"":".")+attribute.getName();
            schemas.pathFieldToAttribute.put(urn+":"+path, attribute);
            if (attribute.getSubAttributes() != null){
                loadAttributes(schemas, urn, attribute.getSubAttributes(), path);
            }
        }
    }
}
