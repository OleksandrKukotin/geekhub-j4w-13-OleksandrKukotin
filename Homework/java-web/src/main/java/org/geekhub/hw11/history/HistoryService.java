package org.geekhub.hw11.history;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @NonNull
    public HistoryEntry getEntry(int id) {
        return historyRepository.getEntry(id)
            .orElseThrow(() -> new IllegalArgumentException("Entry with id " + id + " not found"));
    }

    @NonNull
    public List<HistoryEntry> getEntries() {
        return historyRepository.getEntries();
    }

    @NonNull
    public List<HistoryEntry> getEntries(int userId) {
        return historyRepository.getEntries(userId);
    }

    @NonNull
    public List<HistoryEntry> getEntries(@Nullable Instant from, @Nullable Instant to) {
        if (Objects.nonNull(from) && Objects.nonNull(to) && from.isAfter(to)) {
            throw new IllegalArgumentException("From date must be before to date");
        }

        return historyRepository.getEntries(from, to);
    }

    @NonNull
    public List<HistoryEntry> getEntries(int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1) {
            throw new IllegalArgumentException("Page number and page size must be greater than 0");
        }

        return historyRepository.getEntries(pageNum, pageSize);
    }

    @NonNull
    public List<HistoryEntry> getEntries(int userId, int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1) {
            throw new IllegalArgumentException("Page number and page size must be greater than 0");
        }

        return historyRepository.getEntries(userId, pageNum, pageSize);
    }
}
