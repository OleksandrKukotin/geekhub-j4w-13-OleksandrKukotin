package org.geekhub.hw11.repository;

import org.geekhub.hw11.exception.FileException;
import org.geekhub.hw11.model.LogEntry;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Repository
public class LogRepository {

    private final Path pathToLogFile = Path.of("logs.csv");

    public void writeLogToFile(List<LogEntry> log) {
        if (!Files.exists(pathToLogFile)) {
            try {
                Files.createFile(pathToLogFile);
            } catch (IOException e) {
                throw new FileException(e.getMessage(), e);
            }
        }
        try (BufferedWriter fileWriter = Files.newBufferedWriter(pathToLogFile, StandardOpenOption.CREATE,
            StandardOpenOption.APPEND)) {
            for (LogEntry entry : log) {
                if (!isLineDuplicated(pathToLogFile, entryInCsvFormat(entry))) {
                    fileWriter.write(entryInCsvFormat(entry));
                    fileWriter.newLine();
                    System.out.printf("%nLog for '%s' message successfully saved to the file", entry.input());
                }
            }
            System.out.printf("%nNo more data to save to the file...");
        } catch (IOException e) {
            throw new FileException(e.getMessage(), e);
        }
    }

    private String entryInCsvFormat(LogEntry entry) {
        return String.format("%s,%s,%s,%s", entry.time().toString(), entry.input(), entry.algorithm(), entry.encrypted());
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
        List<LogEntry> parsed = new LinkedList<>();
        try (BufferedReader fileReader = Files.newBufferedReader(pathToLogFile)) {
            String readLine;
            while (true) {
                readLine = fileReader.readLine();
                if (readLine == null || readLine.isBlank()) {
                    break;
                } else {
                    parsed.add(parseLogEntryFromFile(readLine));
                }
            }
        } catch (IOException e) {
            throw new FileException(e.getMessage(), e);
        }
        return parsed;
    }

    private LogEntry parseLogEntryFromFile(String line) {
        String[] parts = line.split(",");
        return new LogEntry(Instant.parse(parts[0]), parts[1], parts[2], parts[3]);
    }
}
