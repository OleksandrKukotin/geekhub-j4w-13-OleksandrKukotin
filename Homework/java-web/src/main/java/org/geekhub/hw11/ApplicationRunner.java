package org.geekhub.hw11;

import org.geekhub.hw11.consoleapi.EncryptTextApi;
import org.geekhub.hw11.consoleapi.LoggerApi;
import org.geekhub.hw11.consoleapi.MainMenu;
import org.geekhub.hw11.exception.FileException;
import org.geekhub.hw11.repository.LogRepository;
import org.geekhub.hw11.service.EncryptionService;
import org.geekhub.hw11.service.LoggingService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ApplicationRunner {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Path logFilePath = Path.of("logFile.txt");
            if (!Files.exists(logFilePath)) {
                Files.createFile(logFilePath);
            }
            LogRepository logRepository = new LogRepository(logFilePath);
            LoggingService logger = new LoggingService(logRepository);
            EncryptionService encryptionService = new EncryptionService(logger);
            EncryptTextApi encryptApi = new EncryptTextApi(scanner, encryptionService);
            LoggerApi logApi = new LoggerApi(logger);

            MainMenu mainMenu = new MainMenu(scanner, encryptApi, logApi);
            mainMenu.printMenu();
        } catch (IOException e) {
            throw new FileException(e.getMessage(), e);
        }
    }
}
