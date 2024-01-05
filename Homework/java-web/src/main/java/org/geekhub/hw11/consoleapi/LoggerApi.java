package org.geekhub.hw11.consoleapi;

import org.geekhub.hw11.service.LoggingService;

public class LoggerApi {

    private final LoggingService loggingService;

    public LoggerApi(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    public void showLog() {
        loggingService.showMessagesLog();
    }

    public void saveToFile() {
        loggingService.writeLogToFile();
    }
}
