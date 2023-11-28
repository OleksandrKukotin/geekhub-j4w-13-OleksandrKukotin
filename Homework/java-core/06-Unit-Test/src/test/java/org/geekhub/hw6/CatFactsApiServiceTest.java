package org.geekhub.hw6;

import com.google.gson.Gson;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.geekhub.hw6.exception.ApiExecutionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class CatFactsApiServiceTest {

    private static final String PROPER_URL = "https://catfact.ninja/fact";

    @Mock
    Gson parser = new Gson();

    @Mock
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    @BeforeEach
    void setUp() {
        CatFactsApiService apiService = new CatFactsApiService(httpClient, parser, PROPER_URL);
    }

    @Test
    void getDataFromApi_withCatFactsApi_shouldReturnNotNull() {
        CatFactsApiService apiService = new CatFactsApiService(httpClient, parser, PROPER_URL);
        assertNotNull(apiService.getDataFromApi());
    }

    @Test
    void getDataFromApi_withWrongUrl_shouldThrowURISyntaxException() {
        CatFactsApiService apiService = new CatFactsApiService(httpClient, parser, "42");
        assertThrows(ApiExecutionException.class, apiService::getDataFromApi);
    }
}
