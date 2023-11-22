package org.geekhub.hw6;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class CatFactsApplication {

    public static void main(String[] args) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        CatFactsApiService apiService = new CatFactsApiService(httpClient);
    }
}
