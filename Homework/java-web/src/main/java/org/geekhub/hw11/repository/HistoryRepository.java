package org.geekhub.hw11.repository;

import org.geekhub.hw11.entity.HistoryEntry;
import org.springframework.lang.NonNull;

import java.util.List;

public interface HistoryRepository {

    void saveEntry(HistoryEntry historyEntry);

    void deleteEntry(int entryId);

    @NonNull
    List<HistoryEntry> findAllEntries();
}
