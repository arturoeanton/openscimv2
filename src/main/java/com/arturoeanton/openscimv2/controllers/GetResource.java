package com.arturoeanton.openscimv2.controllers;

import com.arturoeanton.openscimv2.components.Externalizer;
import com.arturoeanton.openscimv2.components.MongoRepository;
import com.arturoeanton.openscimv2.components.Schemas;
import com.arturoeanton.openscimv2.exceptions.ScimExceptionBase;
import com.arturoeanton.openscimv2.model.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetResource {

    @Autowired
    Schemas schemas;

    @Autowired
    MongoRepository mongoRepository;

    @Autowired
    Externalizer externalizer;

    //  ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/v2/{endpoint}/{id}")
    public ResponseEntity<?> getById(
            @PathVariable String endpoint, @PathVariable String id,
            @RequestParam(value = "attributes", defaultValue = "", required = false) List<String> attributes,
            @RequestParam(value = "excludedAttributes", defaultValue = "", required = false) List<String> excludedAttributes
            ) {
        try {
            var schema = schemas.endpointToSchema.get("/" + endpoint);
            var elementSaved = mongoRepository.get(schema.getName(), id);
            var external = externalizer.external(schema.getId(), elementSaved, attributes, excludedAttributes);
            return new ResponseEntity(external, HttpStatus.OK);
        } catch (ScimExceptionBase eScim) {
            ResponseError re = new ResponseError(HttpStatus.valueOf(eScim.getCode()), eScim.getMessage());
            return new ResponseEntity(re, HttpStatus.resolve(eScim.getCode()));
        }
    }

    @GetMapping("/v2/{endpoint}")
    public ResponseEntity<?> getByAll(
            @PathVariable String endpoint,
            @RequestParam(value = "attributes", defaultValue = "", required = false) List<String> attributes,
            @RequestParam(value = "sortBy", defaultValue = "",  required = false ) List<String>  sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "ascending", required = false) String sortOrder,
            @RequestParam(value = "filter", defaultValue = "", required = false ) String filter,
            @RequestParam(value = "startIndex", defaultValue = "1", required = false) Integer startIndex,
            @RequestParam(value = "count", defaultValue = "0",required = false) Integer count,
            @RequestParam(value = "excludedAttributes", defaultValue = "", required = false) List<String> excludedAttributes
    ) {
        try {
            var schema = schemas.endpointToSchema.get("/" + endpoint);
            var listResponse = mongoRepository.getByQuery(schema, sortBy,sortOrder,filter, startIndex, count);
            return new ResponseEntity(listResponse, HttpStatus.OK);
        } catch (Exception eScim) {
            ResponseError re = new ResponseError(HttpStatus.valueOf(500), eScim.getMessage());
            return new ResponseEntity(re, HttpStatus.resolve(500));
        }
    }

}
