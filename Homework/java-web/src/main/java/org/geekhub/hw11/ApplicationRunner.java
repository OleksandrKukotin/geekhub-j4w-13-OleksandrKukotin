package org.geekhub.hw11;

import org.geekhub.hw11.consoleapi.EncryptTextApi;
import org.geekhub.hw11.consoleapi.MainMenu;
import org.geekhub.hw11.consoleapi.LoggerApi;
import org.geekhub.hw11.service.EncryptionService;
import org.geekhub.hw11.service.LoggingService;

import java.util.Scanner;

public class ApplicationRunner {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            LoggingService logger = new LoggingService();
            EncryptionService encryptionService = new EncryptionService(logger);
            EncryptTextApi encryptApi = new EncryptTextApi(scanner, encryptionService);
            LoggerApi logApi = new LoggerApi(logger);

            MainMenu mainMenu = new MainMenu(scanner, encryptApi, logApi);
            mainMenu.printMenu();
        }
    }
}
