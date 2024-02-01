package org.geekhub.hw11.repository;

import org.geekhub.hw11.exception.RepositoryFileException;
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
public class FileRepository implements org.geekhub.hw11.repository.Repository {

    private final Path pathToLogFile = Path.of("logs.csv");

    @Override
    public void save(List<LogEntry> log) {
        try {
            for (LogEntry entry : log) {
                if (Files.notExists(pathToLogFile)) {
                    Files.createFile(pathToLogFile);
                }
                String lineToSave = entryInCsvFormat(entry);
                if (!isLineDuplicated(lineToSave)) {
                    Files.writeString(this.pathToLogFile, lineToSave + System.lineSeparator(),
                        StandardOpenOption.APPEND);
                    System.out.printf("'%s' message encryption log saved successfully%n", entry.input());
                }
            }
            System.out.println("No more data to save...");
        } catch (IOException e) {
            throw new RepositoryFileException(e.getMessage(), e);
        }
    }

    private String entryInCsvFormat(LogEntry entry) {
        return String.format("%s,%s,%s,%s", entry.time().toString(), entry.input(), entry.algorithm(),
            entry.encrypted());
    }

    private boolean isLineDuplicated(String lineToCheck) {
        AtomicBoolean isDuplicate = new AtomicBoolean(false);
        try (BufferedReader fileReader = Files.newBufferedReader(pathToLogFile)) {
            fileReader.lines().forEach(line -> {
                if (line.contains(lineToCheck)) {
                    isDuplicate.set(true);
                }
            });
        } catch (IOException e) {
            throw new RepositoryFileException(e.getMessage(), e);
        }
        return isDuplicate.get();
    }

    @Override
    public List<LogEntry> getLogs() {
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToLogFile.getFileName().toString()))) {
            return reader.lines()
                .filter(line -> !line.isBlank())
                .map(this::parseLogEntryFromFile)
                .collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            throw new RepositoryFileException(e.getMessage(), e);
        }
    }

    private LogEntry parseLogEntryFromFile(String line) {
        String[] parts = line.split(",");
        return new LogEntry(Instant.parse(parts[0]), parts[1], parts[2], parts[3]);
    }
}
