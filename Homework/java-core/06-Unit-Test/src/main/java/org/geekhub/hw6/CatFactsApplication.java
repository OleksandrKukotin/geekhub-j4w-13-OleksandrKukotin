package org.geekhub.hw6;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CatFactsApplication {

    private static final String CAT_FACT_API_URL = "https://catfact.ninja/fact";
    public static final int FILE_NAME_PROGRAM_ARGUMENT = 0;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("The app hasn't received any arguments! Try to add one to program arguments!");
            System.exit(1);
        }
        HttpClient httpClient = HttpClientBuilder.create().build();
        CatFactsApiService apiService = new CatFactsApiService(httpClient);
        FileService fileService = new FileService(Paths.get(args[FILE_NAME_PROGRAM_ARGUMENT]));
        fileService.writeToFile(apiService.getDataFromApi(CAT_FACT_API_URL));
    }
}
