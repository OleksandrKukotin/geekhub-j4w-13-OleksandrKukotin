package org.geekhub.hw11.service.logging;

import org.geekhub.hw11.model.LogEntry;
import org.geekhub.hw11.repository.FileRepository;
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
class LoggingServiceTest {

    @Mock
    private FileRepository fileRepository;

    private LoggingService loggingService;

    @BeforeEach
    void setUp() {
        loggingService = new LoggingService(fileRepository);
    }

    @Test
    void addToLog() {
        String originalMessage = "Test message";
        String encryptedMessage = "Encrypted message";
        String algorithm = "Caesar cipher";

        loggingService.addToLog(originalMessage, encryptedMessage, algorithm);

        List<LogEntry> logEntries = loggingService.getLog();
        assertEquals(1, logEntries.size());
        assertEquals(originalMessage, logEntries.get(0).input());
        assertEquals(encryptedMessage, logEntries.get(0).encrypted());
        assertEquals(algorithm, logEntries.get(0).algorithm());
    }

    @Test
    void showMessagesLog_EmptyLog() {
        assertDoesNotThrow(() -> loggingService.showMessagesLog());
    }

    @Test
    void showLastMessage_EmptyLog() {
        loggingService.addToLog("orig", "encr", "special cipher");
        assertDoesNotThrow(() -> loggingService.showLastMessage());
    }

    @Test
    void showMessagesLog_NonEmptyLog() {
        String originalMessage = "Test message";
        String encryptedMessage = "Encrypted message";
        String algorithm = "Caesar cipher";

        loggingService.addToLog(originalMessage, encryptedMessage, algorithm);

        assertDoesNotThrow(() -> loggingService.showMessagesLog());
    }

    @Test
    void showLastMessage_NonEmptyLog() {
        String originalMessage = "Test message";
        String encryptedMessage = "Encrypted message";
        String algorithm = "Caesar cipher";

        loggingService.addToLog(originalMessage, encryptedMessage, algorithm);

        assertDoesNotThrow(() -> loggingService.showLastMessage());
    }

    @Test
    void getLogsByDate_ShouldReturnLogsForGivenDate() {
        Instant targetDate = Instant.now();
        loggingService.addToLog("message", "encrypted", "Test cipher");
        loggingService.addToLog("another message", "encrypted", "Test cipher");

        List<LogEntry> result = loggingService.getLogsByDate(targetDate);

        assertEquals(2, result.size());
        // Add more assertions based on your specific log entries
    }

    @Test
    void getAlgorithmUsageCount_ShouldReturnCorrectCount() {
        loggingService.addToLog("test", "ttes", "algorithm1");
        loggingService.addToLog("test", "ttes", "algorithm1");
        loggingService.addToLog("test", "sett", "algorithm2");

        Map<String, Integer> result = loggingService.getAlgorithmUsageCount();

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
        loggingService.addToLog(originalMessage, expectedEncrypted, algorithm);
        List<LogEntry> result = loggingService.getUniqueEncryptions(originalMessage, algorithm);

        assertEquals(1, result.size());
        assertEquals(expectedEncrypted, result.get(0).encrypted());
    }
}
