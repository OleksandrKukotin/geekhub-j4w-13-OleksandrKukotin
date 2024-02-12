package org.geekhub.hw11.history;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface HistoryRepository {

    void saveEntry(HistoryEntry historyEntry);

    void deleteEntry(int entryId);

    @NonNull
    Optional<HistoryEntry> getEntry(int entryId);

    @NonNull
    List<HistoryEntry> getEntries(int pageNum, int pageSize);

    @NonNull
    List<HistoryEntry> getPaginatedEntriesByUserId(int userId);

    @NonNull
    List<HistoryEntry> getPaginatedEntriesByDate(@Nullable OffsetDateTime from, @Nullable OffsetDateTime to);

    @NonNull
    List<HistoryEntry> getPaginatedEntries(int offset, int pageNumber);

    @NonNull
    List<HistoryEntry> getPaginatedEntriesByUserId(int userId, int pageNum, int pageSize);
}
