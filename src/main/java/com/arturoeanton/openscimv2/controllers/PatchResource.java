package com.arturoeanton.openscimv2.controllers;

import com.arturoeanton.openscimv2.components.Externalizer;
import com.arturoeanton.openscimv2.components.MongoRepository;
import com.arturoeanton.openscimv2.components.Schemas;
import com.arturoeanton.openscimv2.exceptions.ScimExceptionBase;
import com.arturoeanton.openscimv2.model.ResponseError;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatchResource {

    @Autowired
    Schemas schemas;

    @Autowired
    MongoRepository mongoRepository;

    @Autowired
    Externalizer externalizer;

    ObjectMapper mapper = new ObjectMapper();

    //TODO: Implementar PATCH
    @PatchMapping("/v2/{endpoint}/{id}")
    public ResponseEntity<?> patch(
            @PathVariable String endpoint, @PathVariable String id, @RequestBody String payload
    ) {
        var eScim = new ScimExceptionBase("Not Implemented", HttpStatus.NOT_IMPLEMENTED.value());
        ResponseError re = new ResponseError(HttpStatus.valueOf(eScim.getCode()), eScim.getMessage());
        return new ResponseEntity<>(re, HttpStatus.resolve(eScim.getCode()));
    }

}
