package org.geekhub.hw11.repository;

import org.geekhub.hw11.model.LogEntry;

import java.util.List;

public class LogRepository {

//     TODO: implement these two methods
//    public List<LogEntry> readLogFromFile() {
//        return new LinkedList<>();
//    }

    public void writeLogToFile(List<LogEntry> log) {
        log.forEach(logEntry -> System.out.printf("%n%tc Input: %s%nOutput:%s via %s", logEntry.time(), logEntry.originalMessage(),
            logEntry.encryptedMessage(), logEntry.algorithm()));
        System.out.printf("%nLog successfully saved to the file");
    }
}
