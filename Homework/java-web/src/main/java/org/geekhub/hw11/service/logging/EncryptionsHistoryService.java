package org.geekhub.hw11.service.logging;

import org.geekhub.hw11.model.LogEntry;
import org.geekhub.hw11.repository.Repository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class EncryptionsHistoryService {

    private final Repository repository;

    public EncryptionsHistoryService(Repository repository) {
        this.repository = repository;
    }

    public void saveLogEntry(LogEntry entry) {
        repository.saveLogEntry(entry);
    }

    public void getAllLogsPaginated(int pageNumber, int offset) {
        printFetchedDataToConsole(repository.fetchPaginatedAll(pageNumber, offset));
    }

    private void printFetchedDataToConsole(List<LogEntry> entries) {
        System.out.printf("| %-30s | %-70s | %-25s | %-70s |%n", "Time", "Message", "Algorithm", "Encrypted");

        for (LogEntry entry : entries) {
            System.out.printf("| %30s | %-70s | %25s | %70s |%n",
                entry.time(),
                entry.input(),
                entry.algorithm(),
                entry.encrypted());
        }
    }

    public void getAllLogsPaginatedByUserId(int userId) {
        repository.fetchPaginatedByUserId(userId);
    }

    public void getLogsByDate(Instant date) {
        repository.fetchByDate(date);
    }

    public void getLogsByEncryptor(String encryptor) {
        repository.fetchByEncryptor(encryptor);
    }
}
