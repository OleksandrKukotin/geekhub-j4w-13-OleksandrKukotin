package org.geekhub.hw11.service;

public class EncryptionService {

    private final LoggingService logger;

    public EncryptionService(LoggingService logger) {
        this.logger = logger;
    }

    public String encryptByCaesarCipher(String originalText, int shift) {
        StringBuilder encryptedText = new StringBuilder();

        for (char character : originalText.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                int shifted = (character - base + shift) % 26 + base;
                encryptedText.append((char) shifted);
            } else {
                encryptedText.append(character);
            }
        }

        logger.addToLog(originalText, encryptedText.toString(), "Caesar cipher");
        return encryptedText.toString();
    }
}
