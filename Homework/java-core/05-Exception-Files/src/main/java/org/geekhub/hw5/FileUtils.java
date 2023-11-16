package org.geekhub.hw5;

import org.geekhub.hw5.exception.FileException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    // TODO: replace all printStackTrace() calls with correct handling
    public FileUtils() {
        // Add something here if there will be some fields in the class
    }

    public static List<String> readAllLines(String file) throws FileException {
        final Path path = Paths.get(file);
        if (Files.notExists(path)) {
            throw new FileException("File doesn't exist!");
        }
        try {
            String fileContent = Files.readString(path);
            return List.of(fileContent.split("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void createDirectories(Path path) {
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(Path path, byte[] content) {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.WRITE)) {
            for (byte character : content) {
                writer.write(character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyToFile(InputStream inputStream, Path path) {
        try {
            writeToFile(path, inputStream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFileIfNotExists(Path path) {
        if (Files.notExists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteDirectories(String directory) throws FileException {
        final Path directoryPath = Paths.get(directory);
        if (Files.notExists(directoryPath)) {
            throw new FileException("File doesn't exist!");
        }
        try {
            List<Path> nestedFiles = Files.walk(directoryPath).toList();
            for (Path filePath : nestedFiles) {
                if (filePath.equals(directoryPath)) {
                    continue;
                }
                if (Files.isDirectory(filePath)) {
                    deleteDirectories(filePath.toString());
                } else {
                    deleteIfExists(filePath);
                }
            }
            deleteIfExists(directoryPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteIfExists(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
