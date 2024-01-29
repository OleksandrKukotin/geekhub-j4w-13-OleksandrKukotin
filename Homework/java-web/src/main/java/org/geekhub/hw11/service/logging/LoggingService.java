package org.geekhub.hw11.service.logging;

import org.geekhub.hw11.model.LogEntry;
import org.geekhub.hw11.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoggingService {

    private final List<LogEntry> log;
    private final LogRepository logRepository;

    public LoggingService(LogRepository logRepository) {
        this.logRepository = logRepository;
        this.log = logRepository.getLogs();
    }

    public void addToLog(String originalMessage, String encryptedMessage, String algorithm) {
        log.add(new LogEntry(Instant.now(), originalMessage, algorithm, encryptedMessage));
    }

    public void showMessagesLog() {
        if (log.isEmpty()) {
            System.out.println("The program log is empty at the moment...");
            return;
        }
        System.out.println("--------- Encrypted messages log ---------");
        log.forEach(entry -> System.out.println(stringForOutput(entry)));
        System.out.println("--------------- End of log ---------------");
    }

    public void showLastMessage() {
        System.out.println(stringForOutput(log.get(log.size() - 1)));
    }

    private String stringForOutput(LogEntry entry) {
        return String.format("%s - Message '%s' was encrypted via %s into '%s'",
            entry.time().toString(), entry.input(), entry.algorithm(), entry.encrypted());
    }

    public void save() {
        logRepository.save(log);
    }

    public List<LogEntry> getLog() {
        return log;
    }

    public List<LogEntry> getLogsByDate(Instant date) {
        return log.stream()
            .filter(logEntry ->
                logEntry.time()
                .truncatedTo(ChronoUnit.DAYS)
                .equals(date.truncatedTo(ChronoUnit.DAYS))
            )
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
