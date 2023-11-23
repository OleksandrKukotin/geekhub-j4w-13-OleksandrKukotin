package org.geekhub.hw6;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.nio.charset.StandardCharsets;

public class CatFactsApplication {

    public static void main(String[] args) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        CatFactsApiService apiService = new CatFactsApiService(httpClient);
        String data = new String(apiService.getDataFromApi("https://catfact.ninja/fact"), StandardCharsets.UTF_8);
        System.out.println(data);
    }
}
