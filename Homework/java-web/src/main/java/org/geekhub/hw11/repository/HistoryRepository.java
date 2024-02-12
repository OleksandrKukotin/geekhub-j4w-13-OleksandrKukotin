package org.geekhub.hw11.repository;

import org.geekhub.hw11.history.HistoryEntry;

import java.time.Instant;
import java.util.List;

public interface HistoryRepository {

    void saveLogEntry(HistoryEntry entry);

    List<HistoryEntry> fetchPaginatedAll(int pageNumber, int offset);

    List<HistoryEntry> fetchPaginatedByUserId(int userId, int pageNumber, int offset);

    List<HistoryEntry> fetchByDate(Instant date);

    List<HistoryEntry> fetchByEncryptor(String encryptor);
}
