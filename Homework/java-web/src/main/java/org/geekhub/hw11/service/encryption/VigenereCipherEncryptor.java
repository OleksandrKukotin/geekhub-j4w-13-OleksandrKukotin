package org.geekhub.hw11.service.encryption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("development")
public class VigenereCipherEncryptor implements Encryptor {

    private final String keyword;

    public VigenereCipherEncryptor(@Value("${vigenere.keyword}") String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String encrypt(String plainText) {
        return plainText.chars()
            .mapToObj(currentChar -> encryptChar((char) currentChar))
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            .toString();
    }

    private char encryptChar(char currentChar) {
        if (Character.isLetter(currentChar)) {
            char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
            int shift = keyword.charAt(keywordIndex++ % keyword.length()) - 'A';

            return (char) ((currentChar - base + shift + 26) % 26 + base);
        } else {
            return currentChar;
        }
    }

    private int keywordIndex = 0;

    @Override
    public String getEncryptorName() {
        return "Vigenere cipher";
    }
}
