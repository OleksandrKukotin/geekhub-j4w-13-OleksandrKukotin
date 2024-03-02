package org.geekhub.hw6;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.protocol.BasicHttpContext;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.geekhub.hw6.exception.ApiExecutionException;

import java.io.InputStream;
import java.net.URI;
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
            HttpHost host = new HttpHost(uri.getHost());
            ClassicHttpRequest request = new HttpGet(uri);
            HttpContext context = new BasicHttpContext();

            try (ClassicHttpResponse response = httpClient.executeOpen(host, request, context)) {
                try (InputStream responseStream = response.getEntity().getContent()) {
                    return parseJsonAsCatFactString(responseStream.readAllBytes());
                }
            }
        } catch (Exception e) {
            throw new ApiExecutionException(e.getMessage(), e);
        }
    }

    private String parseJsonAsCatFactString(byte[] json) {
        return parser.fromJson(new String(json, StandardCharsets.UTF_8), JsonObject.class).get("fact").getAsString();
    }
}
