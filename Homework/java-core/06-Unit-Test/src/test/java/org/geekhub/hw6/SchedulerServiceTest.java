package org.geekhub.hw6;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SchedulerServiceTest {

    @Test
    void testStopScheduler() {
        CatFactsApiService catFactsApiServiceMock = mock(CatFactsApiService.class);
        FileService fileServiceMock = mock(FileService.class);
        SchedulerService schedulerService = new SchedulerService(catFactsApiServiceMock, fileServiceMock, 10);

        schedulerService.stop();

        assertEquals(false, schedulerService.getRunningState());
    }
}
