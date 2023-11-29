package org.geekhub.hw6;

import org.geekhub.hw6.exception.WriteToFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class FileServiceTest {

    private Path testFilePath;
    private FileService fileService;

    @BeforeEach
    void setUp() {
        try {
            testFilePath = Files.createTempFile("test-file", ".txt");
        } catch (IOException e) {
            fail("Error creating temporary file for testing");
        }
        fileService = new FileService(testFilePath);
    }

    @Test
    void testWriteToFile_SuccessfulWrite() {
        String testData = "Test data";

        assertDoesNotThrow(() -> fileService.writeToFile(testData));

        List<String> actualLines = fileService.getActualLines();
        assertEquals(1, actualLines.size());
        assertEquals(testData, actualLines.get(0));
    }

    @Test
    void testWriteToFile_FileCreationError() {
        fileService = new FileService(Paths.get("nonexistent", "directory", "test-file.txt"));
        String testData = "Test data";

        WriteToFileException exception = assertThrows(WriteToFileException.class, () -> fileService.writeToFile(testData));
        assertEquals("I/O error occurred during creating or writing data to file", exception.getMessage());
        assertTrue(exception.getCause() instanceof IOException);
    }

    @Test
    void WriteToFile_WriteError() {
        Path invalidFilePath = Paths.get("'./'./'./invalid-file.cdoijxfiojf");
        FileService invalidFileService = new FileService(invalidFilePath);

        WriteToFileException exception = assertThrows(WriteToFileException.class, () ->
            invalidFileService.writeToFile("Test data"));
        assertEquals("I/O error occurred during creating or writing data to file", exception.getMessage());
        assertTrue(exception.getCause() instanceof IOException);
    }

    @Test
    void IsFileExist_FileExists() {
        assertTrue(fileService.isFileExist());
    }

    @Test
    void IsFileExist_FileDoesNotExist() {
        Path nonExistentPath = Paths.get("nonexistent", "directory", "nonexistent-file.txt");
        FileService nonExistentFileService = new FileService(nonExistentPath);
        assertFalse(nonExistentFileService.isFileExist());
    }
}
