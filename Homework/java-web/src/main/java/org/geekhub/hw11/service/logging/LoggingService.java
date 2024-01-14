package org.geekhub.hw11.service.logging;

import org.geekhub.hw11.model.LogEntry;
import org.geekhub.hw11.repository.LogRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<LogEntry> getLogsByDate(Instant date) {
        return log.stream()
            .filter(logEntry -> logEntry.time().truncatedTo(ChronoUnit.DAYS).equals(date.truncatedTo(ChronoUnit.DAYS)))
            .toList();
    }

    public Map<String, Integer> getAlgorithmUsageCount() {
        Map<String, Integer> algorithmUsageCount = new HashMap<>();

        log.forEach(entry -> algorithmUsageCount.merge(entry.algorithm(), 1, Integer::sum));

        return algorithmUsageCount;
    }

    public List<LogEntry> getUniqueEncryptions(String originalMessage, String algorithm) {
        return log.stream()
            .filter(logEntry -> logEntry.input().equals(originalMessage) && logEntry.algorithm().equals(algorithm))
            .toList();
    }
}
