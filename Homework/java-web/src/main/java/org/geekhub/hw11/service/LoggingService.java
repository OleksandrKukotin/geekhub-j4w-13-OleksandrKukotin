package org.geekhub.hw11.service;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class LoggingService {

    public static final int ORIGINAL_INDEX = 0;
    public static final int ALGORITHM_INDEX = 1;
    public static final int ENCRYPTED_INDEX = 2;
    private final Map<Date, String[]> log;

    public LoggingService() {
        this.log = new LinkedHashMap<>();
    }

    public void addToLog(String originalMessage, String encryptedMessage, String algorithm) {
        this.log.put(Date.from(Instant.now()), new String[]{originalMessage, algorithm, encryptedMessage});
    }

    public void showMessagesLog() {
        if (log.isEmpty()) {
            System.out.printf("%nThe program log is empty at the moment, please try to encrypt messages using " +
                "the E option in the main menu");
            return;
        }
        log.forEach((date, info) ->
            System.out.printf("%n%tc - Message: %s was encrypted via %s into %s", date, info[ORIGINAL_INDEX],
                info[ALGORITHM_INDEX], info[ENCRYPTED_INDEX]));
    }

    //TODO: implement the method below
    public void writeLogToFile() {
        System.out.printf("%nLog successfully saved to the file");
    }
}
