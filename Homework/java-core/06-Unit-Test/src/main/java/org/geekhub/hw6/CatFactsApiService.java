package org.geekhub.hw6;

import org.apache.http.client.HttpClient;

public class CatFactsApiService {

    private final HttpClient httpClient;

    public CatFactsApiService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
