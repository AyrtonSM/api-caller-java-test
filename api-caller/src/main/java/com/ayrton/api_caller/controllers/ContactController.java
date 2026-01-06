package com.ayrton.api_caller.controllers;

import com.ayrton.api_caller.domain.dto.ContactDTO;
import com.ayrton.api_caller.services.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.exc.MismatchedInputException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    public ResponseEntity<List<ContactDTO>> getContacts(@RequestParam(defaultValue = "1") int page,
                                                        @RequestParam(defaultValue = "20") int pageSize){

        try{
            List<ContactDTO> contacts = new ObjectMapper().convertValue(contactService.getContacts(page, pageSize), new TypeReference<List<ContactDTO>>() {});
            return new ResponseEntity<>(contacts, HttpStatus.OK);

        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
        }


    }
}
