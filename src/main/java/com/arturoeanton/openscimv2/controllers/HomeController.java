package com.arturoeanton.openscimv2.controllers;

import com.arturoeanton.openscimv2.components.MongoRepository;
import com.arturoeanton.openscimv2.components.Schemas;
import com.arturoeanton.openscimv2.exceptions.ScimExceptionBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Controller for the home page.
 */

@Log
@Controller
public class HomeController {

    @Autowired
    Schemas schemas;

    @Autowired
    MongoRepository mongoRepository;

    ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/users")
    public String viewUsersPage(Model model) {
        var schema = schemas.endpointToSchema.get("/Users");
        String sortOrder = "";
        String filter = "";
        Integer startIndex = 1;
        Integer count = 0;
        List<String> sortBy = new ArrayList<>();
        var listResponse = mongoRepository.getByQuery(schema,sortBy,sortOrder,filter,startIndex,count);
        model.addAttribute("response", listResponse.getResources());
        return "users";
    }

    @GetMapping("/users/{id}")
    public String viewUsersPage(@PathVariable String id, Model model) throws ScimExceptionBase {
        var schema = schemas.endpointToSchema.get("/Users");
        mongoRepository.delete(schema.getName(), id);
        String sortOrder = "";
        String filter = "";
        Integer startIndex = 1;
        Integer count = 0;
        List<String> sortBy = new ArrayList<>();
        var listResponse = mongoRepository.getByQuery(schema,sortBy,sortOrder,filter,startIndex,count);
        model.addAttribute("response", listResponse.getResources());
        return "redirect:/users";
    }

    @GetMapping("/machines")
    public String viewMachinesPage(Model model) {
        var schema = schemas.endpointToSchema.get("/Machines");
        String sortOrder = "";
        String filter = "";
        Integer startIndex = 1;
        Integer count = 0;
        List<String> sortBy = new ArrayList<>();
        var listResponse = mongoRepository.getByQuery(schema,sortBy,sortOrder,filter,startIndex,count);
        model.addAttribute("response", listResponse.getResources());
        return "machines";
    }

    @GetMapping("/machines/{id}")
    public String viewMachinesPage(@PathVariable String id, Model model) throws ScimExceptionBase {
        var schema = schemas.endpointToSchema.get("/Machines");
        mongoRepository.delete(schema.getName(), id);
        String sortOrder = "";
        String filter = "";
        Integer startIndex = 1;
        Integer count = 0;
        List<String> sortBy = new ArrayList<>();
        var listResponse = mongoRepository.getByQuery(schema,sortBy,sortOrder,filter,startIndex,count);
        model.addAttribute("response", listResponse.getResources());
        return "redirect:/machines";
    }
}