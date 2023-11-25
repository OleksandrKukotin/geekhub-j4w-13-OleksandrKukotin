package org.geekhub.hw6;

import java.util.concurrent.TimeUnit;

public class SchedulerService {

    private static final String END_MESSAGE = "I don't know any new facts";
    private final CatFactsApiService catFactsApiService;
    private final FileService fileService;
    private final int timeInterval;
    private int triesCounter = 0;

    private boolean isRunning = true;

    public SchedulerService(CatFactsApiService catFactsApiService, FileService fileService, int timeInterval) {
        this.catFactsApiService = catFactsApiService;
        this.fileService = fileService;
        this.timeInterval = timeInterval;
    }

    public void start() {
        runScheduler();
    }

    private void runScheduler() {
        while (isRunning) {
            try {
                fetchAndWriteCatFact();
                if (triesCounter > 5) {
                    writeMessageAndStopApp();
                }
                Thread.sleep(TimeUnit.SECONDS.toMillis(timeInterval));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void fetchAndWriteCatFact() {
        String catFactData = catFactsApiService.getDataFromApi();
        if (fileService.isDuplication(catFactData)) {
            triesCounter++;
            return;
        }
        fileService.writeToFile(catFactData);
    }

    private void writeMessageAndStopApp() {
        fileService.writeToFile(END_MESSAGE);
        stop();
    }

    public void stop() {
        isRunning = false;
    }

    public boolean getRunningState() {
        return isRunning;
    }
}
