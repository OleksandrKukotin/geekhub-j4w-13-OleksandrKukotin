package org.geekhub.hw6;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SchedulerServiceTest {

    @Test
    void testFetchAndWriteCatFact_SuccessfulFetch() {
        CatFactsApiService catFactsApiServiceMock = mock(CatFactsApiService.class);
        FileService fileServiceMock = mock(FileService.class);
        SchedulerService schedulerService = new SchedulerService(catFactsApiServiceMock, fileServiceMock, 10);

        when(catFactsApiServiceMock.getDataFromApi()).thenReturn("MockedCatFact");
        when(fileServiceMock.isDuplication("MockedCatFact")).thenReturn(false);

        schedulerService.start();

        verify(catFactsApiServiceMock, atLeastOnce()).getDataFromApi();
        verify(fileServiceMock, atLeastOnce()).writeToFile("MockedCatFact");
    }

    @Test
    void testFetchAndWriteCatFact_Duplicate() {
        CatFactsApiService catFactsApiServiceMock = mock(CatFactsApiService.class);
        FileService fileServiceMock = mock(FileService.class);
        SchedulerService schedulerService = new SchedulerService(catFactsApiServiceMock, fileServiceMock, 10);

        when(catFactsApiServiceMock.getDataFromApi()).thenReturn("MockedCatFact");
        when(fileServiceMock.isDuplication("MockedCatFact")).thenReturn(true);

        schedulerService.start();

        verify(catFactsApiServiceMock, atLeastOnce()).getDataFromApi();
        verify(fileServiceMock, never()).writeToFile("MockedCatFact");
    }

    @Test
    void testFetchAndWriteCatFact_RetryExceeded() {
        CatFactsApiService catFactsApiServiceMock = mock(CatFactsApiService.class);
        FileService fileServiceMock = mock(FileService.class);
        SchedulerService schedulerService = new SchedulerService(catFactsApiServiceMock, fileServiceMock, 10);

        when(catFactsApiServiceMock.getDataFromApi()).thenReturn("MockedCatFact");
        when(fileServiceMock.isDuplication("MockedCatFact")).thenReturn(true);

        schedulerService.start();
        schedulerService.start();
        schedulerService.start();
        schedulerService.start();
        schedulerService.start();
        schedulerService.start();
        schedulerService.start();

        verify(catFactsApiServiceMock, atLeast(6)).getDataFromApi();
        verify(fileServiceMock, atLeastOnce()).writeToFile("I don't know any new facts");
    }

    @Test
    void testStopScheduler() {
        CatFactsApiService catFactsApiServiceMock = mock(CatFactsApiService.class);
        FileService fileServiceMock = mock(FileService.class);
        SchedulerService schedulerService = new SchedulerService(catFactsApiServiceMock, fileServiceMock, 10);

        schedulerService.stop();

        assertEquals(false, schedulerService.getRunningState());
    }
}
