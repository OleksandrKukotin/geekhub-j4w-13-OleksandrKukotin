package org.geekhub.hw11.service.logging;

import org.geekhub.hw11.model.LogEntry;
import org.geekhub.hw11.repository.LogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoggingServiceTest {

    @Mock
    private LogRepository logRepository;

    private LoggingService loggingService;

    @BeforeEach
    void setUp() {
        loggingService = new LoggingService(logRepository);
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
}
