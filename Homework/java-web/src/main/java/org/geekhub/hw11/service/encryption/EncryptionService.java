package org.geekhub.hw11.service.encryption;

import org.geekhub.hw11.service.injection.InjectableService;
import org.geekhub.hw11.service.logging.LoggingService;

public class EncryptionService {

    private final LoggingService logger;
    private final InjectableService injectableService;

    public EncryptionService(LoggingService logger, InjectableService injectableService) {
        this.logger = logger;
        this.injectableService = injectableService;
    }

    public String encrypt(String message, Encryptor encryptor) {
        injectableService.injectTo(encryptor);
        String encryptedText = encryptor.encrypt(message);
        logger.addToLog(message, encryptedText, encryptor.getEncryptorName());
        return encryptedText;
    }
}
