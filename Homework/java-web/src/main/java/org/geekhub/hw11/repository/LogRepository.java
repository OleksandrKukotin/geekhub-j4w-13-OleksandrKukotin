package org.geekhub.hw11.repository;

import org.geekhub.hw11.model.LogEntry;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.OffsetDateTime;
import java.util.List;

public class LogRepository {

    private final Path pathToLogFile;

    public LogRepository(Path pathToLogFile) {
        this.pathToLogFile = pathToLogFile;
    }

    public void writeLogToFile(List<LogEntry> log) throws IOException {
        try (BufferedWriter fileWriter = Files.newBufferedWriter(pathToLogFile, StandardOpenOption.CREATE,
            StandardOpenOption.APPEND)) {
            log.forEach(entry -> {
                try {
                    fileWriter.write(entry.stringForLogFile());
                    fileWriter.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.printf("%nLog successfully saved to the file");
        }
    }

    //TO TEST
    private LogEntry parseLogEntryFromFile(String line) {
        String[] parts = line.split(" ._. ");
        OffsetDateTime time = OffsetDateTime.parse(parts[0]);
        String originalMessage = parts[1];
        String algorithm = parts[2];
        String encryptedMessage = parts[3]; // Remove the trailing "'"
        return new LogEntry(time, originalMessage, encryptedMessage, algorithm);
    }
}
