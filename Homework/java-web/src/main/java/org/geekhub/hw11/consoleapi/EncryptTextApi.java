package org.geekhub.hw11.consoleapi;

import org.geekhub.hw11.service.EncryptionService;

import java.util.Scanner;

public class EncryptTextApi {

    private Scanner scanner;
    private EncryptionService encryptionService;

    public EncryptTextApi(Scanner scanner, EncryptionService encryptionService) {
        this.scanner = scanner;
        this.encryptionService = encryptionService;
    }
}
