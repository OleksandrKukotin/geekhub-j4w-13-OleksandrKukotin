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

    private final String apiUrl;
    private final CloseableHttpClient httpClient;
    private final DataParserService parser;

    public CatFactsApiService(CloseableHttpClient httpClient, DataParserService parser, String apiUrl) {
        this.httpClient = httpClient;
        this.parser = parser;
        this.apiUrl = apiUrl;
    }

    public String getDataFromApi() {
        try {
            URI uri = new URI(apiUrl);
            HttpUriRequest request = new HttpGet(uri);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                return parser.parseJsonAsCatFactString(response.getEntity().getContent().readAllBytes());
            }
        } catch (URISyntaxException | IOException e) {
            throw new ApiExecutionException(e.getMessage(), e);
        }
    }
}
