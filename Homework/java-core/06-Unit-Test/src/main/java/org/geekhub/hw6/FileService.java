package org.geekhub.hw6;

import org.geekhub.hw6.exception.WriteToFileException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileService {

    private final Path fileName;

    public FileService(Path fileName) {
        this.fileName = fileName;
    }

    public void writeToFile(byte[] data) {
        if (createFile()) {
            try {
                byte[] dataWithNewLine = new String(data, StandardCharsets.UTF_8).concat("\n").getBytes();
                Files.write(this.fileName, dataWithNewLine, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
                throw new WriteToFileException("I/O error occurred during writing data to file", e);
            }
        }
    }

    private boolean createFile() {
        if (Files.exists(this.fileName)) {
            return true;
        } else {
            try {
                Files.createFile(this.fileName);
                return true;
            } catch (IOException e) {
                return false;
            }
        }
    }
}
