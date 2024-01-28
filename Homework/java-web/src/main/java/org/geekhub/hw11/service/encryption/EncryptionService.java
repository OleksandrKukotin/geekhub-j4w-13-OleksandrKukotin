package org.geekhub.hw11.service.encryption;

import org.geekhub.hw11.service.logging.LoggingService;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    private final LoggingService logger;
    private final Encryptor encryptor;

    public EncryptionService(LoggingService logger, Encryptor encryptor) {
        this.logger = logger;
        this.encryptor = encryptor;
    }

    public String encrypt(String message) {
        String encryptedText = encryptor.encrypt(message);
        logger.addToLog(message, encryptedText, encryptor.getEncryptorName());
        logger.showLastMessage();
        return encryptedText;
    }
}
