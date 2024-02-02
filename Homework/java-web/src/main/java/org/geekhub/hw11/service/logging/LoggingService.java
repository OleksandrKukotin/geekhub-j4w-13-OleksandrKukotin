package org.geekhub.hw11.service.logging;

import org.geekhub.hw11.model.LogEntry;
import org.geekhub.hw11.repository.Repository;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {

    private final Repository repository;

    public LoggingService(Repository repository) {
        this.repository = repository;
    }

    public void saveLogEntry(LogEntry entry) {
        repository.saveLogEntry(entry);
    }

//    public void fetchByEncryptor() {
//
//    }
//    TODO: implement these two and several more methods using SQL queries in repository
//    public void fetchByDate(Instant date) {
//
//    }
}
