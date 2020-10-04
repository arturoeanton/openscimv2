package com.arturoeanton.openscimv2.controllers;

import com.arturoeanton.openscimv2.components.Externalizer;
import com.arturoeanton.openscimv2.components.MongoRepository;
import com.arturoeanton.openscimv2.components.Schemas;
import com.arturoeanton.openscimv2.exceptions.ScimExceptionBase;
import com.arturoeanton.openscimv2.model.ResponseError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
public class PutResource {

    @Autowired
    Schemas schemas;

    @Autowired
    MongoRepository mongoRepository;

    @Autowired
    Externalizer externalizer;

    ObjectMapper mapper = new ObjectMapper();

    @PutMapping("/v2/{endpoint}/{id}")
    public ResponseEntity<?> put(
            @PathVariable String endpoint, @PathVariable String id, @RequestBody String payload
    ) {
        try {
            var newElement = mapper.readValue(payload, Map.class);
            newElement.remove("id");
            var schema = schemas.endpointToSchema.get("/" + endpoint);
            var oldElement = mongoRepository.get(schema.getName(), id);
            var internal = schemas.validate(endpoint, newElement, oldElement);
            internal.put("id", id);
            schemas.updateMeta(internal, (Map<String, Object>) oldElement.get("meta"));
            mongoRepository.update(schema.getName(), internal);
            var elementSaved = mongoRepository.get(schema.getName(), internal.get("id").toString());
            var external = externalizer.external(schema.getId(),elementSaved);
            return new ResponseEntity(external, HttpStatus.OK);
        } catch (ScimExceptionBase eScim) {
            ResponseError re = new ResponseError(HttpStatus.valueOf(eScim.getCode()), eScim.getMessage());
            return new ResponseEntity(re, HttpStatus.resolve(eScim.getCode()));
        } catch (JsonProcessingException e) {
            var eScim = new ScimExceptionBase(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            ResponseError re = new ResponseError(HttpStatus.valueOf(eScim.getCode()), eScim.getMessage());
            return new ResponseEntity(re, HttpStatus.resolve(eScim.getCode()));
        }
    }

}
