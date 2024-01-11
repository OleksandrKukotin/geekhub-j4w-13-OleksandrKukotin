package org.geekhub.hw11.service.encryption;

import org.geekhub.hw11.service.logging.LoggingService;

public class EncryptionService {

    private final LoggingService logger;

    public EncryptionService(LoggingService logger) {
        this.logger = logger;
    }

    public String encrypt(String message, Encryptor encryptor) {
        String encryptedText = encryptor.encrypt(message);
        logger.addToLog(message, encryptedText, encryptor.getEncryptorName());
        return encryptedText;
    }
}
