package org.geekhub.hw11.repository;

import org.geekhub.hw11.model.LogEntry;

import java.time.Instant;
import java.util.List;

public interface RepositoryEncryption {

    void saveLogEntry(LogEntry entry);

    List<LogEntry> fetchPaginatedAll(int pageNumber, int offset);

    List<LogEntry> fetchPaginatedByUserId(int userId, int pageNumber, int offset);

    List<LogEntry> fetchByDate(Instant date);

    List<LogEntry> fetchByEncryptor(String encryptor);
}
