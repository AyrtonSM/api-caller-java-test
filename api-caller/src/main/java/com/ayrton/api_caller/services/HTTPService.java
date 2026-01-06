package com.ayrton.api_caller.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class HTTPService {

    @Value("${kenect.api.token}")
    private String apiToken;
    @Value("${kenect.api.base-url}")
    private String baseUri;

    public HttpResponse<String> doGetRequest(String path){
        try {
            HttpClient client = HttpClient.newHttpClient();

            String URI = baseUri + path;
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(URI))
                    .headers("Authorization", "Bearer "+ apiToken)
                    .GET()
                    .build();

            try {

                return client.send(
                        httpRequest,
                        HttpResponse.BodyHandlers.ofString()
                );

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }


        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
