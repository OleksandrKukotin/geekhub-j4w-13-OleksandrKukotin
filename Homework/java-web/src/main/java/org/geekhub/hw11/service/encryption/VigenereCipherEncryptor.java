package org.geekhub.hw11.service.encryption;

import org.geekhub.hw11.annotation.Injectable;

public class VigenereCipherEncryptor implements Encryptor {

    private static final String ENCRYPTOR_NAME = "Vigenere cipher";
    @Injectable("vigenere.keyword")
    public String keyword = "";

    public String encrypt(String plainText) {
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0, j = 0; i < plainText.length(); i++) {
            char currentChar = plainText.charAt(i);

            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                int shift = keyword.charAt(j % keyword.length()) - 'A';

                char encryptedChar = (char) ((currentChar - base + shift) % 26 + base);
                encryptedText.append(encryptedChar);

                j++;
            } else {
                encryptedText.append(currentChar);
            }
        }
        return encryptedText.toString();
    }

    public String getEncryptorName() {
        return ENCRYPTOR_NAME;
    }
}
