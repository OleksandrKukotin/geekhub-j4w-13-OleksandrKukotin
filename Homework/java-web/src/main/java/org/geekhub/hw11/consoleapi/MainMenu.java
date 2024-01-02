package org.geekhub.hw11.consoleapi;

import java.util.Scanner;

public class MainMenu {

    private static final String MENU_OPTIONS = """
        Enter the letter to choose command:
            E - for Encrypt text you'll write;
            L - for show Log of your texts;
        """;

    private Scanner scanner;
    private EncryptTextApi encyptApi;
    private ShowLogApi logApi;

    public MainMenu(Scanner scanner, EncryptTextApi encyptApi, ShowLogApi logApi) {
        this.scanner = scanner;
        this.encyptApi = encyptApi;
        this.logApi = logApi;
    }
}
