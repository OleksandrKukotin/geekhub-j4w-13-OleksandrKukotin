package org.geekhub.hw11.service.encryption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("Caesar")
public class CaesarCipherEncryptor implements Encryptor {

    private static final String ENCRYPTOR_NAME = "Caesar cipher";
    private final int key;

    public CaesarCipherEncryptor(@Value("${caesar.key}") int key) {
        this.key = key;
    }

    @Override
    public String encrypt(String originalText) {
        StringBuilder encryptedText = new StringBuilder();

        for (char character : originalText.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                int shifted = (character - base + key) % 26 + base;
                encryptedText.append((char) shifted);
            } else {
                encryptedText.append(character);
            }
        }
        return encryptedText.toString();
    }

    public String getEncryptorName() {
        return ENCRYPTOR_NAME;
    }
}
