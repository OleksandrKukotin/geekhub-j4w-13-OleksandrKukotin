package org.geekhub.hw6;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.nio.file.Paths;

public class CatFactsApplication {

    public static final int FILE_NAME_PROGRAM_ARGUMENT = 0;
    public static final int TIME_ARGUMENT = 1;

    public static void main(String[] args) {
        if (args.length == 0) {
            String exceptionMessage = "The app hasn't received any arguments! Try to add one to program arguments!";
            throw new IllegalArgumentException(exceptionMessage);
        }
        RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(1500)
            .setSocketTimeout(1500)
            .setConnectionRequestTimeout(1500)
            .build();
        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        CatFactsApiService apiService = new CatFactsApiService(httpClient);
        FileService fileService = new FileService(Paths.get(args[FILE_NAME_PROGRAM_ARGUMENT]));
        SchedulerService scheduler = new SchedulerService(apiService, fileService, Integer.parseInt(args[TIME_ARGUMENT]));
        scheduler.start();
    }
}
