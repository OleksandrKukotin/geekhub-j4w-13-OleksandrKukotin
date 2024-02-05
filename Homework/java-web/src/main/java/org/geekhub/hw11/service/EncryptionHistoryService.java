package org.geekhub.hw11.service;

import org.geekhub.hw11.model.LogEntry;
import org.geekhub.hw11.repository.RepositoryEncryption;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class EncryptionHistoryService {

    private final RepositoryEncryption repositoryEncryption;

    public EncryptionHistoryService(RepositoryEncryption repositoryEncryption) {
        this.repositoryEncryption = repositoryEncryption;
    }

    public void saveLogEntry(LogEntry entry) {
        repositoryEncryption.saveLogEntry(entry);
    }

    public void getAllLogsPaginated(int pageNumber, int offset) {
        printFetchedDataToConsole(repositoryEncryption.fetchPaginatedAll(pageNumber, offset));
    }

    private void printFetchedDataToConsole(List<LogEntry> entries) {
        System.out.printf("| %-30s | %-70s | %-25s | %-70s | %-8s |%n",
            "Time", "Message", "Algorithm", "Encrypted", "UserID");

        for (LogEntry entry : entries) {
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
        printFetchedDataToConsole(repositoryEncryption.fetchPaginatedByUserId(userId, pageNumber, offset));
    }

    public void getLogsByDate(Instant date) {
        printFetchedDataToConsole(repositoryEncryption.fetchByDate(date));
    }

    public void getLogsByEncryptor(String encryptor) {
        printFetchedDataToConsole(repositoryEncryption.fetchByEncryptor(encryptor));
    }
}
