package org.geekhub.hw11.consoleapi;

import org.geekhub.hw11.service.EncryptionService;

import java.util.Scanner;

public class EncryptTextApi {

    private final Scanner scanner;
    private final EncryptionService encryptionService;

    public EncryptTextApi(Scanner scanner, EncryptionService encryptionService) {
        this.scanner = scanner;
        this.encryptionService = encryptionService;
    }

    public void encryptInput() {
        System.out.printf("%nPlease, enter the text you wanna encrypt: ");
        String input = scanner.nextLine();
        String encrypted = encryptionService.encryptByCaesarCipher(input, 3);
        System.out.printf("%nYour message: %s%nEncrypted message: %s", input, encrypted);
    }
}
