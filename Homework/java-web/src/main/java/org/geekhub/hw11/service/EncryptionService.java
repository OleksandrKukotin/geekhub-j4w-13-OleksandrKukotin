package org.geekhub.hw11.service;

import com.geekhub.hw15.encoding.EncodingAlgorithm;
import com.geekhub.hw15.encoding.EncodingOperation;
import com.geekhub.hw15.encoding.EncodingService;
import org.geekhub.hw11.entity.HistoryEntry;
import org.geekhub.hw11.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class EncryptionService {

    private final EncodingService encodingService;
    private final HistoryRepository historyRepository;

    public EncryptionService(EncodingService encodingService, HistoryRepository historyRepository) {
        this.encodingService = encodingService;
        this.historyRepository = historyRepository;
    }

    public void encrypt(long userId, String message, EncodingAlgorithm algorithm, EncodingOperation operation) {
        String encrypted = encodingService.encode(algorithm, operation, message);
        HistoryEntry entry = new HistoryEntry(
            null,
            Instant.now(),
            message,
            encrypted,
            algorithm,
            userId,
            operation);
        historyRepository.saveEntry(entry);
    }
}
