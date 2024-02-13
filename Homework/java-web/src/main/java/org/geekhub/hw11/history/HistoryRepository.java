package org.geekhub.hw11.history;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface HistoryRepository {

    void saveEntry(HistoryEntry historyEntry);

    void deleteEntry(int entryId);

    @NonNull
    Optional<HistoryEntry> getEntry(int id);

    @NonNull
    List<HistoryEntry> getEntries();

    @NonNull
    List<HistoryEntry> getEntries(int userId);

    @NonNull
    List<HistoryEntry> getEntries(@Nullable Instant from, @Nullable Instant to);

    @NonNull
    List<HistoryEntry> getEntries(int pageNum, int pageSize);

    @NonNull
    List<HistoryEntry> getEntries(int userId, int pageNum, int pageSize);
}
