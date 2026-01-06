package com.ayrton.api_caller.services;

import com.ayrton.api_caller.domain.Contact;
import com.ayrton.api_caller.domain.dto.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;
import java.util.List;

@Service
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
        return new ObjectMapper().readValue(responseString.body(), new TypeReference<List<Contact>>() {});

    }

}
