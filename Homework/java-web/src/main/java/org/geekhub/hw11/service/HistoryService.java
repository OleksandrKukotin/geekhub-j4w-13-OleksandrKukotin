package org.geekhub.hw11.service;

import org.geekhub.hw11.entity.HistoryEntry;
import org.geekhub.hw11.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    private final HistoryRepository repository;

    public HistoryService(HistoryRepository repository) {
        this.repository = repository;
    }

    public void saveEntry(HistoryEntry entry) {
        repository.saveEntry(entry);
    }

    public void deleteEntry(int entryId) {
        repository.deleteEntry(entryId);
    }

    public List<HistoryEntry> getAllEntries() {
        return repository.findAllEntries();
    }
}
