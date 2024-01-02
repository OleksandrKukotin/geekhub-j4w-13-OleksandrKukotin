package org.geekhub.hw11.consoleapi;

import org.geekhub.hw11.service.LoggingService;

public class ShowLogApi {

    private LoggingService loggingService;

    public ShowLogApi(LoggingService loggingService) {
        this.loggingService = loggingService;
    }
}
