package com.ayrton.api_caller.services;

import com.ayrton.api_caller.exceptions.ExternalApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
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
                log.error(e.getMessage());
                throw new ExternalApiException("Failed to call external API", e);
            }


        } catch (URISyntaxException e) {
            log.error(e.getMessage());
            throw new ExternalApiException("Failed to call external API", e);
        }
    }
}
