package com.ayrton.api_caller.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping(value = {"", "/"})
    public ResponseEntity<String> index(){
        return new ResponseEntity<>("Api Caller V1", HttpStatus.OK);
    }
}
