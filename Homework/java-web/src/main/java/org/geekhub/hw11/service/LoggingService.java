package org.geekhub.hw11.service;

import java.util.HashMap;
import java.util.Map;

public class LoggingService {

    private final Map<String, String> log;

    public LoggingService() {
        this.log = new HashMap<>();
    }

    public void addToLog(String originalMessage, String encryptedMessage) {
        this.log.put(originalMessage, encryptedMessage);
    }

    public void showMessagesLog() {
        log.forEach((originalMessage, encryptedMessage) ->
            System.out.println("Original: " + originalMessage + ", Encrypted: " + encryptedMessage));
    }
}
