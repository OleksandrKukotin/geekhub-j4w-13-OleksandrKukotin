package org.geekhub.hw11.repository;

import org.geekhub.hw11.exception.FileException;
import org.geekhub.hw11.model.LogEntry;

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

public class LogRepository {

    private final Path pathToLogFile;

    public LogRepository(Path pathToLogFile) {
        this.pathToLogFile = pathToLogFile;
    }

    public void writeLogToFile(List<LogEntry> log) {
        try (BufferedWriter fileWriter = Files.newBufferedWriter(pathToLogFile, StandardOpenOption.CREATE,
            StandardOpenOption.APPEND)) {
            for (LogEntry entry : log) {
                if (!checkForDuplicates(pathToLogFile, entry.stringForLogFile())) {
                    fileWriter.write(entry.stringForLogFile());
                    fileWriter.newLine();
                }
            }
            System.out.printf("%nLog successfully saved to the file");
        } catch (IOException e) {
            throw new FileException(e.getMessage(), e);
        }
    }

    private boolean checkForDuplicates(Path pathToFile, String lineToCheck) {
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
        String[] parts = line.split(" ._. ");
        return new LogEntry(Instant.parse(parts[0]), parts[1], parts[2], parts[3]);
    }
}
