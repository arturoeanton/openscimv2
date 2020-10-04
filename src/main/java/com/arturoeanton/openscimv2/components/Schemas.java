package com.arturoeanton.openscimv2.components;

import com.arturoeanton.openscimv2.commons.Config;
import com.arturoeanton.openscimv2.commons.Transformer;
import com.arturoeanton.openscimv2.exceptions.ScimExceptionBase;
import com.arturoeanton.openscimv2.model.Attribute;
import com.arturoeanton.openscimv2.model.Schema;
import com.arturoeanton.openscimv2.model.SchemaExtension;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Schemas {

    public Map<String, Schema> urnToSchema = new HashMap<>();
    public Map<String, List<SchemaExtension>> urnToExtensions = new HashMap<>();
    public Map<String, Schema> endpointToSchema = new HashMap<>();
    public Map<String, Attribute> pathFieldToAttribute = new HashMap<>();

    public Map<String, Object> validate(String endpoint, Map<String, Object> element, Map<String, Object> oldElement) throws ScimExceptionBase {
        Map<String, Object> internalElement = new HashMap<>();
        var schema = endpointToSchema.get("/" + endpoint);
        if (schema == null) {
            throw new ScimExceptionBase("Not found Schema", HttpStatus.NOT_FOUND.value());
        }
        var extensions = urnToExtensions.get(schema.getId());

        var elementSchemas = (List<String>) element.get("schemas");
        if (elementSchemas == null) {
            internalElement.put("schemas", Collections.singletonList(schema.getId()));
        }
        Set<String> setOfSchemas = new HashSet<>((Collection<String>) element.get("schemas"));
        internalElement.put("schemas", setOfSchemas);
        validateIsRequired(schema.getAttributes(), element, schema.getId());
        validateWithSchema(schema.getId(), "", element, oldElement, internalElement, "");
        validateSchemaAndExtensions(schema, extensions, setOfSchemas);
        return internalElement;

    }

    private void validateSchemaAndExtensions(Schema schema, List<SchemaExtension> extensions, Set<String> setOfSchemas) throws ScimExceptionBase {
        for (var s : setOfSchemas) {
            if (s.equals(schema.getId())) {
                continue;
            }
            if (extensions.stream().filter(x -> x.getSchema().startsWith(s)).findFirst().isEmpty()) {
                throw new ScimExceptionBase(String.format("%s Does not has %s extensions", schema.getId(), s), HttpStatus.NOT_FOUND.value());
            }
        }
        for (var ext : extensions.stream().filter(x -> x.isRequired()).collect(Collectors.toList())) {
            if (!setOfSchemas.contains(ext.getSchema())) {
                throw new ScimExceptionBase(String.format("%s is required", ext.getSchema()), HttpStatus.NOT_FOUND.value());
            }
        }
    }

    private void validateWithSchema(
            String urnBase, String urnExtension, Map<String, Object> element, Map<String, Object> oldElement, Map<String, Object> internalElement, String oldPath
    ) throws ScimExceptionBase {
        String urn = "".equals(urnExtension) ? urnBase : urnExtension;
        Set<String> setOfSchemas = (Set<String>) internalElement.get("schemas");
        for (var key : element.keySet()) {
            if (key.equals("schemas")) continue;
            var value = element.get(key);
            var oldValue = (oldElement != null ? oldElement.get(key) : null);
            var path = oldPath + ("".equals(oldPath) ? "" : ".") + key;
            var attribute = pathFieldToAttribute.get(urn + ":" + path);
            if (attribute == null) {
                // maybe the path is urn
                var schemaExtension = "".equals(urnExtension) ? urnToSchema.get(path) : urnToSchema.get(urnExtension);
                if (schemaExtension == null) {
                    throw new ScimExceptionBase(String.format("Path not found (%s)", path), 400);
                }
                var optionalExtension = urnToExtensions.get(urnBase)
                        .stream().filter(x -> x.getSchema().equals(schemaExtension.getId())).findFirst();
                if (optionalExtension.isEmpty()) {
                    throw new ScimExceptionBase(String.format("Extension Schema accept (%s)->(%s)", urnBase, path), 400);
                }
                setOfSchemas.add(path);
                validateIsRequired(schemaExtension.getAttributes(), (Map<String, Object>) value, path);
                var subInternalElement = new HashMap<String, Object>();
                internalElement.put(Transformer.internal(key), subInternalElement);
                validateWithSchema(urnBase, path, (Map<String, Object>) value, (Map<String, Object>) oldValue, subInternalElement, "");
                continue;
            }
            if (attribute.isMultiValued()) {
                Set<Object> set = new HashSet<>();
                var list = (List<Object>) value;
                for (int i = 0; i < list.size(); i++) {
                    var v = list.get(i);
                    Object oldV = null;
                    if (oldValue != null) {
                        oldV = ((List<Object>) oldValue).get(i);
                    }
                    var iv = new HashMap<>();
                    v = validateField(urnBase, urnExtension, key, v, oldV, iv, path, attribute);
                    set.add(v);
                }
                internalElement.put(Transformer.internal(key), set);

                continue;
            }
            value = validateField(urnBase, urnExtension, key, value, oldValue, internalElement, path, attribute);
            internalElement.put(Transformer.internal(key), value);
        }
    }

    private Object validateField(String urnBase, String urnExtension, String key, Object value, Object oldValue, Object internal, String path, Attribute attribute) throws ScimExceptionBase {
        var flagComplex = validateComplexType(urnBase, urnExtension, key, value, oldValue, internal, path, attribute);
        if (flagComplex) {
            value = ((Map) internal).get(key);
        }
        if (!flagComplex) {
            value = validateDateTimeType(value, path, attribute);
            value = validateReferenceType(value, path, attribute);
            value = validateDecimalType(value, path, attribute);
            value = validateIntegerType(value, path, attribute);
            value = validateBooleanType(value, path, attribute);
            validateCanonicalValues(attribute.getCanonicalValues(), value);
        }
        validateMutability(attribute, value, oldValue, path);
        return value;
    }

    private void validateMutability(Attribute attribute, Object value, Object oldValue, String path) throws ScimExceptionBase {
        if ("readOnly".equals(attribute.getMutability())) {
            if (oldValue != null && !value.equals(oldValue)) {
                throw new ScimExceptionBase(String.format("%s is mutability %s", path, attribute.getMutability()), 400);
            }
        }
    }

    private void validateCanonicalValues(List<String> canonicalValues, Object value) throws ScimExceptionBase {
        if (canonicalValues != null) {
            if (canonicalValues.size() > 0) {
                if (!canonicalValues.contains(value)) {
                    throw new ScimExceptionBase(String.format("value (%s) not in  %s", value.toString(), canonicalValues.toString()), 400);
                }
            }
        }
    }

    private Object validateBooleanType(Object value, String path, Attribute attribute) throws ScimExceptionBase {
        if ("boolean".equals(attribute.getType())) {
            try {
                value = Boolean.parseBoolean(value.toString());
            } catch (ClassCastException e) {
                throw new ScimExceptionBase(String.format("Field %s should be boolean type", path), 400);
            }
        }
        return value;
    }


    private Object validateReferenceType(Object value, String path, Attribute attribute) {
        if ("reference".equals(attribute.getType())) {
            return null;
        }
        return value;
    }

    private Object validateDateTimeType(Object value, String path, Attribute attribute) throws ScimExceptionBase {
        if ("datetime".equals(attribute.getType())) {
            var format = Config.FORMAT_DATE;
            try {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);
                var date = LocalDateTime.parse(value.toString(), dateFormatter);
                value = Timestamp.valueOf(date).getTime();
            } catch (DateTimeParseException e) {
                throw new ScimExceptionBase(String.format("Field %s should be date type format(%s)", path, format), 400);
            }
        }
        return value;
    }

    private Object validateDecimalType(Object value, String path, Attribute attribute) throws ScimExceptionBase {
        if ("decimal".equals(attribute.getType())) {
            try {
                value = Float.parseFloat(value.toString());
            } catch (NumberFormatException e) {
                throw new ScimExceptionBase(String.format("Field %s should be decimal type", path), 400);
            }
        }
        return value;
    }

    private Object validateIntegerType(Object value, String path, Attribute attribute) throws ScimExceptionBase {
        if ("integer".equals(attribute.getType())) {
            try {
                value = Integer.parseInt(value.toString());
            } catch (NumberFormatException e) {
                throw new ScimExceptionBase(String.format("Field %s should be integer type", path), 400);
            }
        }
        return value;
    }

    private boolean validateComplexType(String urnBase, String urnExtension, String key, Object value, Object oldValue, Object internalValue, String oldPath, Attribute attribute) throws ScimExceptionBase {
        if ("complex".equals(attribute.getType())) {
            var map = (Map<String, Object>) value;
            var oldMap = (Map<String, Object>) oldValue;
            var internal = (Map<String, Object>) internalValue;
            validateIsRequired(attribute.getSubAttributes(), map, oldPath);
            var newComplex = new HashMap<String, Object>();
            internal.put(Transformer.internal(key), newComplex);
            validateWithSchema(urnBase, urnExtension, map, oldMap, newComplex, oldPath);
            return true;
        }
        return false;
    }

    private void validateIsRequired(List<Attribute> attributes, Map<String, Object> map, String path) throws ScimExceptionBase {
        for (var a : attributes) {
            if (a.isRequired()) {
                if (map.get(a.getName()) == null) {
                    throw new ScimExceptionBase(String.format("%s -> %s is required", path, a.getName()), 400);
                }
            }
        }
    }


    public Map<String, Object> createMeta(Map<String, Object> elem, String endpoint) {
        var schema = endpointToSchema.get("/" + endpoint);
        if (elem.get("id") == null) {
            elem.put("id", UUID.randomUUID().toString());
        }
        var meta = new HashMap<String, Object>();


        var date = new Date();
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        var sdf = new SimpleDateFormat(Config.FORMAT_DATE);
        final String hex = java.util.Base64.getEncoder().encodeToString(bytes);
        meta.put("resourceType", schema.getName());
        meta.put("created", sdf.format(date));
        meta.put("lastModified", sdf.format(date));
        meta.put("version", "W\\/\"" + hex + "\"");
        meta.put("location", "/" + endpoint + "/" + elem.get("id"));
        elem.put("meta", meta);
        return elem;
    }

    public Map<String, Object> updateMeta(Map<String, Object> elem, Map<String, Object> meta) throws ScimExceptionBase {
        if (elem.get("id") == null) {
            throw new ScimExceptionBase("Error update meta element without Id", 500);
        }
        var date = new Date();
        SecureRandom random = new SecureRandom();
        var sdf = new SimpleDateFormat(Config.FORMAT_DATE);
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        final String hex = java.util.Base64.getEncoder().encodeToString(bytes);
        meta.put("lastModified", sdf.format(date));
        meta.put("version", "W\\/\"" + hex + "\"");
        elem.put("meta", meta);
        return elem;
    }
}