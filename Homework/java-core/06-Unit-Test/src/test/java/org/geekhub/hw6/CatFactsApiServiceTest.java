package org.geekhub.hw6;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CatFactsApiServiceTest {

    @Test
    void testCatFactsApiService() throws IOException {
        // Set up your dependencies and class instance
        CloseableHttpClient httpClientMock = mock(CloseableHttpClient.class);
        DataParserService parserMock = mock(DataParserService.class);
        CatFactsApiService catFactsApiService = new CatFactsApiService(httpClientMock, parserMock);

        // Mock the behavior of your dependencies
        when(httpClientMock.execute(any(HttpUriRequest.class)))
                .thenReturn(mock(CloseableHttpResponse.class));
        when(parserMock.parseJsonAsCatFactString(any(byte[].class)))
                .thenReturn("MockedCatFact");

        // Execute the method you want to test
        String catFact = catFactsApiService.getDataFromApi();

        // Verify and assert the result
        assertEquals("MockedCatFact", catFact);
        verify(httpClientMock, atLeastOnce()).execute(any(HttpUriRequest.class));
        verify(parserMock, atLeastOnce()).parseJsonAsCatFactString(any(byte[].class));

        // Additional assertions based on your specific logic
    }
}
