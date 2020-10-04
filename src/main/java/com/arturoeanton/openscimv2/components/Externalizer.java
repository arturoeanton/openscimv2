package com.arturoeanton.openscimv2.components;

import com.arturoeanton.openscimv2.commons.Config;
import com.arturoeanton.openscimv2.commons.Transformer;
import com.arturoeanton.openscimv2.model.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class Externalizer {
    @Autowired
    Schemas schemas;


    public Map<String, Object> external(String urnBase, Map<String, Object> internal, List<String> attributes, List<String> excludedAttributes) {
        Map<String, Object> external = new HashMap<>();
        external(urnBase, urnBase, internal, external, attributes, excludedAttributes, "");
        return external;
    }

    public Map<String, Object> external(String urnBase, Map<String, Object> internal) {
        return external(urnBase, internal, new ArrayList<>(), new ArrayList<>());
    }

    private void external(String urnBase, String urn, Map<String, Object> internal, Map<String, Object> external, List<String> attributes, List<String> excludedAttributes, String path) {
        for (var key : internal.keySet()) {
            var pathField = path + (!"".equals(path) ? "." : "") + Transformer.external(key);
            var value = internal.get(key);
            var fullPath = urn + ":" + pathField;
            if (CheckAttributesAndExcludedAttributes(urnBase, urn, attributes, excludedAttributes, pathField, fullPath)){
                continue;
            }
            if ("schemas".equals(pathField)) {
                external.put("schemas", value);
                continue;
            }
            if ("meta".equals(pathField)) {
                external.put("meta", value);
                continue;
            }
            if ("id".equals(pathField)) {
                external.put("id", value);
                continue;
            }
            var attribute = schemas.pathFieldToAttribute.get(fullPath);
            var nextUrn = urn;
            if (attribute == null) {
                nextUrn = Transformer.external(key);
                pathField = "";
            }

            if (attribute != null && "writeOnly".equalsIgnoreCase(attribute.getMutability())) {
                continue;
            }

            if (attribute != null && attribute.isMultiValued()) {
                var set = new HashSet<>();
                var list = (Collection<?>) value;
                for (var item : list) {
                    if ("complex".equals(attribute.getType())) {
                        var newItem = new HashMap<String, Object>();
                        addValue(urnBase, newItem, key, pathField, item, attribute, attributes, excludedAttributes, nextUrn);
                        set.add(newItem);
                        continue;
                    }
                    set.add(item);
                }
                external.put(Transformer.external(key), set);
                continue;
            }
            addValue(urnBase, external, key, pathField, value, attribute, attributes, excludedAttributes, nextUrn);
        }
    }

    private boolean CheckAttributesAndExcludedAttributes(String urnBase, String urn, List<String> attributes, List<String> excludedAttributes, String pathField, String fullPath) {
        if (excludedAttributes.size() > 0) {
            if (urnBase.equals(urn)) {
                if (excludedAttributes.contains(pathField)) {
                    return true;
                }
            } else {
                if (excludedAttributes.contains(fullPath)) {
                    return true;
                }
            }
        }
        if (attributes.size() > 0) {
            if (urnBase.equals(urn)) {
                var currentPath = pathField;
                var flag = attributes.stream().filter(a-> a.startsWith(currentPath)).findFirst().isEmpty();
                if (flag) {
                    return true;
                }
            } else {
                var currentPath = fullPath;
                var flag = attributes.stream().filter(a-> a.startsWith(currentPath)).findFirst().isEmpty();
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    private void addValue(String urnBase, Map<String, Object> external, String key, String pathField, Object value, Attribute attribute, List<String> attributes, List<String> excludedAttributes, String nextUrn) {
        if ("".equals(pathField) || "complex".equalsIgnoreCase(attribute.getType())) {
            var externalValue = new HashMap<String, Object>();
            external(urnBase, nextUrn, (Map) value, externalValue, attributes, excludedAttributes, pathField);
            external.put(Transformer.external(key), externalValue);
            return;
        }
        if ("datetime".equalsIgnoreCase(attribute.getType())) {
            var timestamp = new Timestamp((long) value);
            LocalDateTime localDate = timestamp.toLocalDateTime();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Config.FORMAT_DATE);
            value = localDate.format(dateFormatter);
            external.put(Transformer.external(key), value);
            return;
        }
        external.put(Transformer.external(key), value);
    }


}
