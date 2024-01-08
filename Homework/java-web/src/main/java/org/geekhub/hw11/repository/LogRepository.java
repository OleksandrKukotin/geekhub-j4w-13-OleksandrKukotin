package org.geekhub.hw11.repository;

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

public class LogRepository {

    private final Path pathToLogFile;

    public LogRepository(Path pathToLogFile) {
        this.pathToLogFile = pathToLogFile;
    }

    public void writeLogToFile(List<LogEntry> log) throws IOException {
        try (BufferedWriter fileWriter = Files.newBufferedWriter(pathToLogFile, StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
             BufferedReader fileReader = Files.newBufferedReader(pathToLogFile)) {
            for (LogEntry entry : log) {
                String lineToWrite = entry.stringForLogFile();
                if (fileReader.lines().noneMatch(line -> line.contains(lineToWrite))) {
                    fileWriter.write(lineToWrite);
                    fileWriter.newLine();
                } else {
                    break;
                }
            }
            System.out.printf("%nLog successfully saved to the file");
        }
    }

    public List<LogEntry> parseLogHistory() {
        List<LogEntry> parsed = new LinkedList<>();
        try (BufferedReader fileReader = Files.newBufferedReader(pathToLogFile)) {
            String readLine = "";
            while (true) {
                readLine = fileReader.readLine();
                if (readLine == null || readLine.isBlank()) {
                    break;
                } else {
                    parsed.add(parseLogEntryFromFile(readLine));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return parsed;
    }

    private LogEntry parseLogEntryFromFile(String line) {
        String[] parts = line.split(" ._. ");
        return new LogEntry(Instant.parse(parts[0]), parts[1], parts[2], parts[3]);
    }
}
