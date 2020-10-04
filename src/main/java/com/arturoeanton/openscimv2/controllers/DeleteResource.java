package com.arturoeanton.openscimv2.controllers;

import com.arturoeanton.openscimv2.components.Externalizer;
import com.arturoeanton.openscimv2.components.MongoRepository;
import com.arturoeanton.openscimv2.components.Schemas;
import com.arturoeanton.openscimv2.exceptions.ScimExceptionBase;
import com.arturoeanton.openscimv2.model.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeleteResource {

    @Autowired
    Schemas schemas;

    @Autowired
    MongoRepository mongoRepository;

    @Autowired
    Externalizer externalizer;

    //  ObjectMapper mapper = new ObjectMapper();

    @DeleteMapping("/v2/{endpoint}/{id}")
    public ResponseEntity<?> getById(
            @PathVariable String endpoint, @PathVariable String id,
            @RequestParam(value = "attributes", defaultValue = "", required = false) List<String> attributes,
            @RequestParam(value = "excludedAttributes", defaultValue = "", required = false) List<String> excludedAttributes
            ) {
        try {
            var schema = schemas.endpointToSchema.get("/" + endpoint);
            mongoRepository.delete(schema.getName(), id);
            return new ResponseEntity("", HttpStatus.NO_CONTENT);
        } catch (ScimExceptionBase eScim) {
            ResponseError re = new ResponseError(HttpStatus.valueOf(eScim.getCode()), eScim.getMessage());
            return new ResponseEntity(re, HttpStatus.resolve(eScim.getCode()));
        }
    }

}
