package org.geekhub.hw6;

import org.geekhub.hw6.exception.WriteToFileException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileServiceTest {

    @Test
    void testWriteToFile_SuccessfulWrite() throws IOException {
        // Arrange
        Path filePath = Paths.get("test-file.txt");
        FileService fileService = new FileService(filePath);

        // Act
        assertDoesNotThrow(() -> fileService.writeToFile("Test data"));

        // Assert
        assertTrue(filePath.toFile().exists());
        assertEquals(Collections.singletonList("Test data"), Files.readAllLines(filePath));
    }

    @Test
    void testWriteToFile_FileCreationError() throws IOException {
        // Arrange
        Path filePath = mock(Path.class);
        FileService fileService = new FileService(filePath);

        // Mock file creation failure
        when(filePath.toFile().exists()).thenReturn(false);
        when(Files.createFile(filePath)).thenThrow(new IOException("File creation error"));

        // Act and Assert
        WriteToFileException exception = assertThrows(WriteToFileException.class, () -> fileService.writeToFile("Test data"));
        assertEquals("I/O error occurred during creating file", exception.getMessage());
        assertTrue(exception.getCause() instanceof IOException);
    }

    @Test
    void testWriteToFile_WriteError() throws IOException {
        // Arrange
        Path filePath = Paths.get("test-file.txt");
        FileService fileService = new FileService(filePath);

        // Mock Files.write to throw an IOException
        doThrow(new IOException("Write error")).when(Files.class);
        Files.write(filePath, Collections.singletonList("Test data"), StandardOpenOption.APPEND);

        // Act and Assert
        WriteToFileException exception = assertThrows(WriteToFileException.class, () -> fileService.writeToFile("Test data"));
        assertEquals("I/O error occurred during writing data to file", exception.getMessage());
        assertTrue(exception.getCause() instanceof IOException);
    }

    @Test
    void testIsDuplication_NoDuplication() throws IOException {
        // Arrange
        Path filePath = Paths.get("test-file.txt");
        Files.write(filePath, Arrays.asList("Fact 1", "Fact 2"), StandardOpenOption.CREATE);

        FileService fileService = new FileService(filePath);

        // Act and Assert
        assertFalse(fileService.isDuplication("New Fact"));
    }

    @Test
    void testIsDuplication_WithDuplication() throws IOException {
        // Arrange
        Path filePath = Paths.get("test-file.txt");
        Files.write(filePath, Arrays.asList("Fact 1", "Fact 2"), StandardOpenOption.CREATE);

        FileService fileService = new FileService(filePath);

        // Act and Assert
        assertTrue(fileService.isDuplication("Fact 1"));
    }
}
