package org.geekhub.hw11.service.encryption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("Caeasar")
public class CaesarCipherEncryptor implements Encryptor {

    private final int key;

    public CaesarCipherEncryptor(@Value("${caesar.key}") int key) {
        this.key = key;
    }

    @Override
    public String encrypt(String originalText) {
        return originalText.chars()
            .mapToObj(character -> encryptChar((char) character))
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            .toString();
    }

    private char encryptChar(char character) {
        if (Character.isLetter(character)) {
            char base = Character.isUpperCase(character) ? 'A' : 'a';
            int shifted = (character - base + key) % 26 + base;
            return (char) shifted;
        } else {
            return character;
        }
    }

    @Override
    public String getEncryptorName() {
        return "Caesar cipher";
    }
}
