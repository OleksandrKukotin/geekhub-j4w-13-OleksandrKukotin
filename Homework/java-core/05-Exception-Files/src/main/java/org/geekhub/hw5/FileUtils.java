package org.geekhub.hw5;

import org.geekhub.hw5.exception.DeleteDirectoryException;
import org.geekhub.hw5.exception.FileException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class FileUtils {

    private FileUtils() {

    }

    public static List<String> readAllLines(String file) throws FileException {
        final Path path = Path.of(file);
        if (Files.notExists(path)) {
            throw new FileException("File doesn't exist!");
        }
        try {
            return Files.readAllLines(path);
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
        try {
            Files.write(path, content);
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
        Path directoryPath = Path.of(directory);
        if (Files.notExists(directoryPath)) {
            throw new FileException("File doesn't exist!");
        }

        try (Stream<Path> walker = Files.walk(directoryPath)) {
            walker.sorted(Comparator.reverseOrder())
                .forEach(path -> {
                    try {
                        deleteIfExists(path);
                    } catch (FileException e) {
                        throw new DeleteDirectoryException(e.getMessage(), e);
                    }
                });
        } catch (IOException e) {
            throw new FileException("Something went wrong");
        }
    }

    public static void deleteIfExists(Path path) throws FileException {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new FileException("Something went wrong with file you tried to delete");
        }
    }
}
