package org.geekhub.hw6;

import com.google.gson.Gson;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;

import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CatFactsApplication {

    private static final String CAT_FACT_API_URL = "https://catfact.ninja/fact";
    private static final String STOP_MESSAGE = "I don't know any new facts";
    private static final int FILE_NAME_PROGRAM_ARGUMENT = 0;
    private static final int TIME_INTERVAL_PROGRAM_ARGUMENT = 1;
    private static final int MAX_TRIES_NUMBER = 5;
    private static boolean isRunning = true;

    public static void main(String[] args) {
        if (args.length == 0) {
            String exceptionMessage = "The app hasn't received any arguments! Try to add one to program arguments!";
            throw new IllegalArgumentException(exceptionMessage);
        }
        RequestConfig requestConfig = RequestConfig.DEFAULT;
        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
        CatFactsApiService apiService = new CatFactsApiService(httpClient, new Gson(), CAT_FACT_API_URL);
        FileService fileService = new FileService(Paths.get(args[FILE_NAME_PROGRAM_ARGUMENT]));
        List<String> existingFactLines = fileService.getActualLines();
        int tries = 1;
        while (isRunning) {
            String fact = apiService.getDataFromApi();
            if (existingFactLines.contains(fact)) {
                tries++;
                if (tries > MAX_TRIES_NUMBER) {
                    isRunning = false;
                    fileService.writeToFile(STOP_MESSAGE);
                }
                continue;
            }
            fileService.writeToFile(fact);
            try {
                TimeUnit.SECONDS.sleep(Integer.parseInt(args[TIME_INTERVAL_PROGRAM_ARGUMENT]));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}
