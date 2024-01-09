package org.geekhub.hw11.service;

import org.geekhub.hw11.model.LogEntry;
import org.geekhub.hw11.repository.LogRepository;

import java.time.Instant;
import java.util.List;

public class LoggingService {

    private final List<LogEntry> log;
    private final LogRepository logRepository;

    public LoggingService(LogRepository logRepository) {
        this.logRepository = logRepository;
        this.log = logRepository.parseLogHistory();
    }

    public void addToLog(String originalMessage, String encryptedMessage, String algorithm) {
        this.log.add(new LogEntry(Instant.now(), originalMessage, algorithm, encryptedMessage));
    }

    public void showMessagesLog() {
        if (log.isEmpty()) {
            System.out.printf("%nThe program log is empty at the moment, please try to encrypt messages using " +
                "the E option in the main menu");
            return;
        }
        log.forEach(entry -> System.out.println(entry.stringForOutput()));
    }

    public void save() {
        logRepository.writeLogToFile(log);
    }
}
