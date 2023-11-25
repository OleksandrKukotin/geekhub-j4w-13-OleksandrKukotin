package org.geekhub.hw6;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DataParserServiceTest {

    @Test
    void parseJsonAsCatFactString() {
        // Set up your dependencies and class instance
        Gson gsonMock = mock(Gson.class);
        DataParserService dataParserService = new DataParserService(gsonMock);

        // Mock the behavior of your dependencies
        when(gsonMock.fromJson(any(String.class), eq(JsonObject.class)))
                .thenReturn(createMockJsonObject("MockedCatFact"));

        // Execute the method you want to test
        String catFact = dataParserService.parseJsonAsCatFactString("{\"fact\":\"MockedCatFact\"}".getBytes());

        // Verify and assert the result
        assertEquals("MockedCatFact", catFact);
        verify(gsonMock, atLeastOnce()).fromJson(any(String.class), eq(JsonObject.class));

        // Additional assertions based on your specific logic
    }

    private JsonObject createMockJsonObject(String fact) {
        JsonObject jsonObjectMock = new JsonObject();
        jsonObjectMock.addProperty("fact", fact);
        return jsonObjectMock;
    }
}
