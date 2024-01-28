package org.geekhub.hw11.repository;

import org.geekhub.hw11.exception.FileException;
import org.geekhub.hw11.model.LogEntry;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Repository
public class LogRepository {

    private final Path pathToLogFile = Path.of("logs.csv");

    public void writeLogToFile(List<LogEntry> log) {
        try {
            for (LogEntry entry : log) {
                String lineToSave = entryInCsvFormat(entry);
                if (!isLineDuplicated(pathToLogFile, lineToSave)) {
                    Files.writeString(pathToLogFile, lineToSave + System.lineSeparator(),
                        StandardOpenOption.APPEND);
                    System.out.printf("'%s' message encryption log saved successfully%n", entry.input());
                }
            }
            System.out.println("No more data to save...");
        } catch (IOException e) {
            throw new FileException(e.getMessage(), e);
        }
    }

    private String entryInCsvFormat(LogEntry entry) {
        return String.format("%s,%s,%s,%s", entry.time().toString(), entry.input(), entry.algorithm(),
            entry.encrypted());
    }

    private boolean isLineDuplicated(Path pathToFile, String lineToCheck) {
        AtomicBoolean isDuplicate = new AtomicBoolean(false);
        try (BufferedReader fileReader = Files.newBufferedReader(pathToFile)) {
            fileReader.lines().forEach(line -> {
                if (line.contains(lineToCheck)) {
                    isDuplicate.set(true);
                }
            });
        } catch (IOException e) {
            throw new FileException(e.getMessage(), e);
        }
        return isDuplicate.get();
    }

    public List<LogEntry> parseLogHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToLogFile.getFileName().toString()))){
            return reader.lines()
                .filter(line -> !line.isBlank())
                .map(this::parseLogEntryFromFile)
                .collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            throw new FileException(e.getMessage(), e);
        }
    }

    private LogEntry parseLogEntryFromFile(String line) {
        String[] parts = line.split(",");
        return new LogEntry(Instant.parse(parts[0]), parts[1], parts[2], parts[3]);
    }
}
