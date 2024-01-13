package org.geekhub.hw11.service.encryption;

import org.geekhub.hw11.annotation.Injectable;

import java.util.concurrent.atomic.AtomicInteger;

public class VigenereCipherEncryptor implements Encryptor {

    private static final String ENCRYPTOR_NAME = "Vigenere cipher";
    @Injectable("vigenere.keyword")
    public String keyword = "";

    public String encrypt(String plainText) {
        StringBuilder encryptedText = new StringBuilder();

        AtomicInteger j = new AtomicInteger(0);
        plainText.chars().mapToObj(currentChar -> (char) currentChar)
            .forEach(currentChar -> {
                if (Character.isLetter(currentChar)) {
                    char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                    int shift = keyword.charAt(j.get() % keyword.length()) - 'A';

                    char encryptedChar = (char) ((currentChar - base + shift) % 26 + base);
                    encryptedText.append(encryptedChar);

                    j.getAndIncrement();
                } else {
                    encryptedText.append(currentChar);
                }
            });
        return encryptedText.toString();
    }

    public String getEncryptorName() {
        return ENCRYPTOR_NAME;
    }
}
