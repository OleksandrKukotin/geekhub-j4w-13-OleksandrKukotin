package org.geekhub.hw6;

import org.geekhub.hw6.exception.WriteToFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileService {

    private final Path filePath;

    public FileService(Path filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(String data) {
        if (createFile()) {
            try {
                byte[] dataWithNewLine = data.concat("\n").getBytes();
                Files.write(this.filePath, dataWithNewLine, StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new WriteToFileException("I/O error occurred during writing data to file", e);
            }
        } else {
            throw new WriteToFileException("I/O error occurred during creating file", new IOException());
        }
    }

    private boolean createFile() {
        if (Files.exists(filePath)) {
            return true;
        } else {
            try {
                Files.createFile(filePath);
                return true;
            } catch (IOException e) {
                return false;
            }
        }
    }

    public boolean isDuplication(String data) {
        try {
            List<String> readFacts = Files.readAllLines(filePath);
            for (String line : readFacts) {
                if (line.contains(data)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
