package com.ayrton.api_caller;

import com.ayrton.api_caller.services.HTTPService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(properties = {
        "kenect.api.token=${KENECT_API_TOKEN}"
})
class HTTPServiceTest {

    @Autowired
    private HTTPService httpService;

    private static MockWebServer mockWebServer;

    @BeforeAll
    static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void teardown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void shouldSendGetRequestWithAuthorizationHeader() throws Exception {

        String path = "/contacts";
        String baseUrl = mockWebServer.url("").toString();

        HttpResponse<String> response =
                httpService.doGetRequest(path);

        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("ok"));
    }
}

