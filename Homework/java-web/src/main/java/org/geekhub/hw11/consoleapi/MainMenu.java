package org.geekhub.hw11.consoleapi;

import java.util.Scanner;

public class MainMenu {

    private static final String MENU_OPTIONS = """

        Enter the letter to choose command:
            E - for Encrypt text you'll write;
            L - for show Log of your texts;
            Q - for finish executing the program
        """;

    private final Scanner scanner;
    private final EncryptTextApi encryptApi;
    private final LoggerApi logApi;

    public MainMenu(Scanner scanner, EncryptTextApi encryptApi, LoggerApi logApi) {
        this.scanner = scanner;
        this.encryptApi = encryptApi;
        this.logApi = logApi;
    }

    public void printMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println(MENU_OPTIONS);

            switch (scanner.nextLine()) {
                case "E" -> encryptApi.encryptInput();
                case "L" -> logApi.showLog();
                case "Q" -> {
                    logApi.saveToFile();
                    isRunning = false;
                }
                default -> System.out.println("Ooops! Something went wrong :[");
            }
        }
    }
}
