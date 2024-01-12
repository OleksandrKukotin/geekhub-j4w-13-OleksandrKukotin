package org.geekhub.hw11.consoleapi;

import org.geekhub.hw11.service.encryption.CaesarCipherEncryptor;
import org.geekhub.hw11.service.encryption.EncryptionService;
import org.geekhub.hw11.service.encryption.Encryptor;
import org.geekhub.hw11.service.encryption.VigenereCipherEncryptor;

import java.util.Scanner;

public class EncryptTextApi {

    private static final String ENCRYPT_SUB_MENU = """
        Choice the method of encryption:
            1 - Caesar cipher;
            2 - Vigenere cipher;""";
    private static final String WRONG_INPUT_MESSAGE = "Wrong input, please enter the one of the numbers in menu";

    private final Scanner scanner;
    private final EncryptionService encryptionService;

    public EncryptTextApi(Scanner scanner, EncryptionService encryptionService) {
        this.scanner = scanner;
        this.encryptionService = encryptionService;
    }

    public void encryptInput() {
        Encryptor encryptor = null;
        while (encryptor == null) {
            System.out.println(ENCRYPT_SUB_MENU);
            switch (scanner.nextLine()) {
                case "1" -> encryptor = new CaesarCipherEncryptor();
                case "2" -> encryptor = new VigenereCipherEncryptor();
                default -> System.out.println(WRONG_INPUT_MESSAGE);
            }
        }

        System.out.printf("%nPlease, enter the text you wanna encrypt: ");
        String input = scanner.nextLine();
        String encrypted = encryptionService.encrypt(input, encryptor);
        System.out.printf("%nYour message: %s%nEncrypted message: %s", input, encrypted);
    }
}
