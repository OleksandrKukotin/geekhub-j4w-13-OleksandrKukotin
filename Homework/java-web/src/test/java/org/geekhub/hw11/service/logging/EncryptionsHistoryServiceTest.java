package org.geekhub.hw11.service.logging;

import org.geekhub.hw11.model.LogEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EncryptionsHistoryServiceTest {

    @Mock
    private FileRepository fileRepository;

    private EncryptionsHistoryService encryptionsHistoryService;

    @BeforeEach
    void setUp() {
        encryptionsHistoryService = new EncryptionsHistoryService(fileRepository);
    }

    @Test
    void addToLog() {
        String originalMessage = "Test message";
        String encryptedMessage = "Encrypted message";
        String algorithm = "Caesar cipher";

        encryptionsHistoryService.addToLog(originalMessage, encryptedMessage, algorithm);

        List<LogEntry> logEntries = encryptionsHistoryService.getLog();
        assertEquals(1, logEntries.size());
        assertEquals(originalMessage, logEntries.get(0).input());
        assertEquals(encryptedMessage, logEntries.get(0).encrypted());
        assertEquals(algorithm, logEntries.get(0).algorithm());
    }

    @Test
    void showMessagesLog_EmptyLog() {
        assertDoesNotThrow(() -> encryptionsHistoryService.showMessagesLog());
    }

    @Test
    void showLastMessage_EmptyLog() {
        encryptionsHistoryService.addToLog("orig", "encr", "special cipher");
        assertDoesNotThrow(() -> encryptionsHistoryService.showLastMessage());
    }

    @Test
    void showMessagesLog_NonEmptyLog() {
        String originalMessage = "Test message";
        String encryptedMessage = "Encrypted message";
        String algorithm = "Caesar cipher";

        encryptionsHistoryService.addToLog(originalMessage, encryptedMessage, algorithm);

        assertDoesNotThrow(() -> encryptionsHistoryService.showMessagesLog());
    }

    @Test
    void showLastMessage_NonEmptyLog() {
        String originalMessage = "Test message";
        String encryptedMessage = "Encrypted message";
        String algorithm = "Caesar cipher";

        encryptionsHistoryService.addToLog(originalMessage, encryptedMessage, algorithm);

        assertDoesNotThrow(() -> encryptionsHistoryService.showLastMessage());
    }

    @Test
    void getLogsByDate_ShouldReturnLogsForGivenDate() {
        Instant targetDate = Instant.now();
        encryptionsHistoryService.addToLog("message", "encrypted", "Test cipher");
        encryptionsHistoryService.addToLog("another message", "encrypted", "Test cipher");

        List<LogEntry> result = encryptionsHistoryService.getLogsByDate(targetDate);

        assertEquals(2, result.size());
        // Add more assertions based on your specific log entries
    }

    @Test
    void getAlgorithmUsageCount_ShouldReturnCorrectCount() {
        encryptionsHistoryService.addToLog("test", "ttes", "algorithm1");
        encryptionsHistoryService.addToLog("test", "ttes", "algorithm1");
        encryptionsHistoryService.addToLog("test", "sett", "algorithm2");

        Map<String, Integer> result = encryptionsHistoryService.getAlgorithmUsageCount();

        assertEquals(2, result.size());
        assertEquals(2, result.get("algorithm1"));
        assertEquals(1, result.get("algorithm2"));
        // Add more assertions based on your specific log entries
    }

    @Test
    void getUniqueEncryptions_ShouldReturnUniqueEncryptions() {
        String originalMessage = "message1";
        String expectedEncrypted = "top secret";
        String algorithm = "algorithm1";
        encryptionsHistoryService.addToLog(originalMessage, expectedEncrypted, algorithm);
        List<LogEntry> result = encryptionsHistoryService.getUniqueEncryptions(originalMessage, algorithm);

        assertEquals(1, result.size());
        assertEquals(expectedEncrypted, result.get(0).encrypted());
    }
}
