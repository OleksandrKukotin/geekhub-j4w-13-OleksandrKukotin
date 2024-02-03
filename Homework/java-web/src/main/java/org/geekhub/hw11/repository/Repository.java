package org.geekhub.hw11.repository;

import org.geekhub.hw11.model.LogEntry;

import java.time.Instant;
import java.util.List;

public interface Repository {

    void saveLogEntry(LogEntry entry);

    List<LogEntry> fetchPaginatedAll(int pageNumber, int offset);

    List<LogEntry> fetchPaginatedByUserId(int userId);

    List<LogEntry> fetchByDate(Instant date);

    List<LogEntry> fetchByEncryptor(String encryptor);
}
