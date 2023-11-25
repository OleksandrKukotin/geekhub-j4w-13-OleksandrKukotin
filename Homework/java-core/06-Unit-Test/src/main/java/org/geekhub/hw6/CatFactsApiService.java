package org.geekhub.hw6;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.geekhub.hw6.exception.ApiExecutionException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class CatFactsApiService {

    private static final String CAT_FACT_API_URL = "https://catfact.ninja/fact";
    private final HttpClient httpClient;

    public CatFactsApiService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public byte[] getDataFromApi() {
        try {
            URI uri = new URI(CAT_FACT_API_URL);
            HttpUriRequest request = new HttpGet(uri);
            HttpResponse response = httpClient.execute(request);
            return response.getEntity().getContent().readAllBytes();
        } catch (ClientProtocolException e) {
            throw new ApiExecutionException(e.getMessage(), e);
        } catch (URISyntaxException | IOException e) {
            throw new ApiExecutionException(e.getMessage(), e);
        }
    }
}
