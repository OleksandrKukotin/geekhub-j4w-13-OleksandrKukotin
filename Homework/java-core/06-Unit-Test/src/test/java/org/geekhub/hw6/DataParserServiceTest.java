package org.geekhub.hw6;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;

class DataParserServiceTest {

    @Mock
    Gson gson = new Gson();

    @Test
    void canCreateInstance() {
        DataParserService parserService = new DataParserService(gson);
        assertInstanceOf(DataParserService.class, parserService);
    }

    @Test
    void parseJsonAsCatFactString_withCorrectJson_returnFact() {
        DataParserService dataParserService = new DataParserService(gson);

        String catFact = dataParserService.parseJsonAsCatFactString("{\"fact\":\"MockedCatFact\"}".getBytes());

        assertEquals("MockedCatFact", catFact);
    }

    @Test
    void parseJsonAsCatFactString_withJsonThatHavent_Fact_shouldThrowNullPointerException() {
        DataParserService dataParserService = new DataParserService(gson);

        byte[] data = "{\"notFact\":\"MockedCatFact\"}".getBytes();

        assertThrows(NullPointerException.class, () -> dataParserService.parseJsonAsCatFactString(data);
    }
}
