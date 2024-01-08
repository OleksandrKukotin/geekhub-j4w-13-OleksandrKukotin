package org.geekhub.hw11.service;

import org.geekhub.hw11.model.LogEntry;
import org.geekhub.hw11.repository.LogRepository;

import java.time.OffsetDateTime;
import java.util.LinkedList;
import java.util.List;

public class LoggingService {

    private final List<LogEntry> log;
    private final LogRepository logRepository;

    public LoggingService(LogRepository logRepository) {
        this.log = new LinkedList<>();
        this.logRepository = logRepository;
    }

    public void addToLog(String originalMessage, String encryptedMessage, String algorithm) {
        this.log.add(new LogEntry(OffsetDateTime.now(), originalMessage, encryptedMessage, algorithm));
    }

    public void showMessagesLog() {
        if (log.isEmpty()) {
            System.out.printf("%nThe program log is empty at the moment, please try to encrypt messages using " +
                "the E option in the main menu");
            return;
        }
        log.forEach(entry ->
            System.out.printf("%n%tc - Message: '%s' was encrypted via %s into '%s'", entry.time(), entry.originalMessage(),
                entry.algorithm(), entry.encryptedMessage()));
        System.out.println();
    }

    public void save() {
        logRepository.writeLogToFile(log);
    }
}
