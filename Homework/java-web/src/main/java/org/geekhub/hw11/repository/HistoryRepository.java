package org.geekhub.hw11.repository;

import org.geekhub.hw11.entity.HistoryEntry;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface HistoryRepository {

    void saveEntry(HistoryEntry historyEntry);

    void deleteEntry(int entryId);

    @NonNull
    Optional<HistoryEntry> findEntryByEntryId(int entryId);

    @NonNull
    List<HistoryEntry> findAllEntriesByUserId();

    @NonNull
    List<HistoryEntry> findAllEntriesByUserId(int userId);
}
