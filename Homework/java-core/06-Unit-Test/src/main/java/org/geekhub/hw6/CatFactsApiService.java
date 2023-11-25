package org.geekhub.hw6;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.geekhub.hw6.exception.ApiExecutionException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class CatFactsApiService {

    private static final String CAT_FACT_API_URL = "https://catfact.ninja/fact";
    private final CloseableHttpClient httpClient;
    private final DataParserService parser;

    public CatFactsApiService(CloseableHttpClient httpClient, DataParserService parser) {
        this.httpClient = httpClient;
        this.parser = parser;
    }

    public String getDataFromApi() {
        try {
            URI uri = new URI(CAT_FACT_API_URL);
            HttpUriRequest request = new HttpGet(uri);
            try (CloseableHttpResponse response = httpClient.execute(request)){
                return parser.parseJsonAsCatFactString(response.getEntity().getContent().readAllBytes());
            }
        } catch (URISyntaxException | IOException e) {
            throw new ApiExecutionException(e.getMessage(), e);
        }
    }
}
