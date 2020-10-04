package com.arturoeanton.openscimv2.controllers;

import com.arturoeanton.openscimv2.components.Externalizer;
import com.arturoeanton.openscimv2.components.MongoRepository;
import com.arturoeanton.openscimv2.components.Schemas;
import com.arturoeanton.openscimv2.exceptions.ScimExceptionBase;
import com.arturoeanton.openscimv2.model.ResponseError;
import com.arturoeanton.openscimv2.model.Search;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

@RestController
public class PostResource {

    @Autowired
    Schemas schemas;

    @Autowired
    MongoRepository mongoRepository;

    @Autowired
    Externalizer externalizer;

    @Autowired
    GetResource getResource;

    ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/v2/{endpoint}")
    public ResponseEntity<?> post(
            @PathVariable String endpoint, @RequestBody String payload
    ) {
        try {
            var newElement = mapper.readValue(payload, Map.class);
            var schema = schemas.endpointToSchema.get("/" + endpoint);
            var internal = schemas.validate(endpoint, newElement, null);
            internal.put("id", UUID.randomUUID().toString());
            schemas.createMeta(internal,endpoint);
            mongoRepository.save(schema.getName(), internal);

            var elementSaved = mongoRepository.get(schema.getName(), internal.get("id").toString());
            var external = externalizer.external(schema.getId(),elementSaved);
            return new ResponseEntity(external, HttpStatus.OK);
        } catch (ScimExceptionBase eScim) {
            ResponseError re = new ResponseError(HttpStatus.valueOf(eScim.getCode()), eScim.getMessage());
            return new ResponseEntity<>(re, HttpStatus.resolve(eScim.getCode()));
        } catch (JsonProcessingException e) {
            var eScim = new ScimExceptionBase(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            ResponseError re = new ResponseError(HttpStatus.valueOf(eScim.getCode()), eScim.getMessage());
            return new ResponseEntity<>(re, HttpStatus.resolve(eScim.getCode()));
        }
    }


    @PostMapping("/v2/{endpointResource}/.search")
    public ResponseEntity<?> postSearch(@PathVariable String endpointResource, @RequestBody String payload) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            var search = objectMapper.readValue(payload, Search.class);
            return getResource.getByAll(
                    endpointResource,
                    search.getAttributes(), search.getExcludedAttributes(),
                    search.getSortBy(), search.getSortOrder(),
                    search.getFilter(),
                    search.getStartIndex(), search.getCount()
            );
        } catch (Exception e) {
            var eScim = new ScimExceptionBase(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
            ResponseError re = new ResponseError(HttpStatus.valueOf(eScim.getCode()), eScim.getMessage());
            return new ResponseEntity<>(re, HttpStatus.resolve(eScim.getCode()));
        }
    }


}
