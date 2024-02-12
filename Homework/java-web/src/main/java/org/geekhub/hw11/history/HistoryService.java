package org.geekhub.hw11.history;

import org.geekhub.hw11.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void saveLogEntry(HistoryEntry entry) {
        historyRepository.saveLogEntry(entry);
    }

    public void getAllLogsPaginated(int pageNumber, int offset) {
        printFetchedDataToConsole(historyRepository.fetchPaginatedAll(pageNumber, offset));
    }

    private void printFetchedDataToConsole(List<HistoryEntry> entries) {
        System.out.printf("| %-30s | %-70s | %-25s | %-70s | %-8s |%n",
            "Time", "Message", "Algorithm", "Encrypted", "UserID");

        for (HistoryEntry entry : entries) {
            System.out.printf("| %30s | %-70s | %25s | %70s | %-8d |%n",
                entry.time(),
                entry.input(),
                entry.algorithm(),
                entry.encrypted(),
                entry.userId());
        }

        System.out.println();
    }

    public void getAllLogsPaginatedByUserId(int userId, int pageNumber, int offset) {
        printFetchedDataToConsole(historyRepository.fetchPaginatedByUserId(userId, pageNumber, offset));
    }

    public void getLogsByDate(Instant date) {
        printFetchedDataToConsole(historyRepository.fetchByDate(date));
    }

    public void getLogsByEncryptor(String encryptor) {
        printFetchedDataToConsole(historyRepository.fetchByEncryptor(encryptor));
    }
}
