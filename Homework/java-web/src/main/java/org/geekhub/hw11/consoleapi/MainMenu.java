package org.geekhub.hw11.consoleapi;

import java.util.Scanner;

public class MainMenu {

    private static final String MENU_OPTIONS = """

        Enter one of the following capital letters to choose the command:
            E - for Encrypt text you'll write;
            L - for interacting with the program Log;
            Q - for finish executing the program""";
    public static final String WRONG_INPUT_MESSAGE = "Probably you entered the wrong symbol, " +
        "try to use one capital letter from listed in the menu";

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
                case "L" -> logApi.printLogMenu();
                case "Q" -> {
                    logApi.saveLog();
                    isRunning = false;
                }
                default -> System.out.println(WRONG_INPUT_MESSAGE);
            }
        }
    }
}
