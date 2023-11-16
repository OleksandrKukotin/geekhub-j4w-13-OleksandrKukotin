package org.geekhub.hw5;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileUtils {

    public FileUtils() {
        // Add something here if there will be some fields in the class
    }

    public static List<String> readAllLines(String file) {
        return file.lines().toList();
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

    public static void deleteDirectories(String directory) {
        try {
            Files.deleteIfExists(Paths.get(directory));
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
