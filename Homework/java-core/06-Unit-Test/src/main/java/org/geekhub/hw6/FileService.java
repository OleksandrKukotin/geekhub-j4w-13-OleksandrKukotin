package org.geekhub.hw6;

import org.geekhub.hw6.exception.WriteToFileException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileService {

    private final Path filePath;

    public FileService(Path filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(byte[] data) {
        if (createFile()) {
            if (isDuplication(data)) {
                System.out.println("It's duplicate :/");
                return;
            }
            try {
                byte[] dataWithNewLine = new String(data, StandardCharsets.UTF_8).concat("\n").getBytes();
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

    private boolean isDuplication(byte[] data) {
        try {
            List<String> readFacts = Files.readAllLines(filePath);
            String dataToCheck = new String(data, StandardCharsets.UTF_8);
            for (String line : readFacts) {
                if (line.contains(dataToCheck)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
