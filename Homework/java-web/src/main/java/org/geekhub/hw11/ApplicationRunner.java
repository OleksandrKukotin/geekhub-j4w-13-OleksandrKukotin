package org.geekhub.hw11;

import org.geekhub.hw11.consoleapi.EncryptTextApi;
import org.geekhub.hw11.consoleapi.MainMenu;
import org.geekhub.hw11.consoleapi.ShowLogApi;
import org.geekhub.hw11.service.EncryptionService;
import org.geekhub.hw11.service.LoggingService;

import java.util.Scanner;

public class ApplicationRunner {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            EncryptionService encryptionService = new EncryptionService();
            EncryptTextApi encryptApi = new EncryptTextApi(scanner, encryptionService);
            LoggingService logger = new LoggingService();
            ShowLogApi logApi = new ShowLogApi(logger);
            MainMenu mainMenu = new MainMenu(scanner, encryptApi, logApi);
        }
    }
}
