package com.ayrton.api_caller.services;

import com.ayrton.api_caller.domain.Contact;
import com.ayrton.api_caller.domain.dto.ContactDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.exc.MismatchedInputException;

import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ContactService {

    @Autowired
    private HTTPService httpService;

    public List<Contact> getContacts(int page, int pageSize){

        String path = String.format(
                "/contacts?page=%d&pageSize=%d",
                page,
                pageSize
        );

        HttpResponse<String>  responseString = httpService.doGetRequest(path);
        try{
            return new ObjectMapper().readValue(responseString.body(), new TypeReference<List<Contact>>() {});
        }catch (MismatchedInputException e){
            log.error(e.getMessage());
            return Collections.emptyList();
        }


    }

}
