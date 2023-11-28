package org.geekhub.hw6;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.geekhub.hw6.exception.ApiExecutionException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class CatFactsApiService {

    private final String apiUrl;
    private final CloseableHttpClient httpClient;
    private final Gson parser;

    public CatFactsApiService(CloseableHttpClient httpClient, Gson parser, String apiUrl) {
        this.httpClient = httpClient;
        this.parser = parser;
        this.apiUrl = apiUrl;
    }

    public String getDataFromApi() {
        try {
            URI uri = new URI(apiUrl);
            HttpUriRequest request = new HttpGet(uri);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                try (InputStream responseStream = response.getEntity().getContent()) {
                    return parseJsonAsCatFactString(responseStream.readAllBytes());
                }
            }
        } catch (URISyntaxException | IOException e) {
            throw new ApiExecutionException(e.getMessage(), e);
        }
    }

    private String parseJsonAsCatFactString(byte[] json) {
        return parser.fromJson(new String(json, StandardCharsets.UTF_8), JsonObject.class).get("fact").getAsString();
    }
}
