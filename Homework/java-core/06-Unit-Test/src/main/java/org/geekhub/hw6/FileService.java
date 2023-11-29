package org.geekhub.hw6;

import org.geekhub.hw6.exception.WriteToFileException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    private final Path filePath;

    public FileService(Path filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(String data) {
        if (isFileExist()) {
            try {
                byte[] dataWithNewLine = data.concat("\n").getBytes();
                Files.write(this.filePath, dataWithNewLine, StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new WriteToFileException(e.getMessage(), e);
            }
        } else {
            throw new WriteToFileException("I/O error occurred during creating or writing data to file", new IOException());
        }
    }

    public boolean isFileExist() {
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }

    public List<String> getActualLines() {
        try {
            return Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}
