package org.geekhub.hw11.service.encryption;

import org.geekhub.hw11.service.injection.InjectableService;
import org.geekhub.hw11.service.logging.LoggingService;

import java.util.Properties;

public class EncryptionService {

    private final LoggingService logger;
    private final InjectableService injectableService;
    private final Properties encryptionParameters;

    public EncryptionService(LoggingService logger, InjectableService injectableService, Properties encryptionParameters) {
        this.logger = logger;
        this.injectableService = injectableService;
        this.encryptionParameters = encryptionParameters;
    }

    public String encrypt(String message, Encryptor encryptor) {
        injectableService.injectTo(encryptor, encryptionParameters);
        String encryptedText = encryptor.encrypt(message);
        logger.addToLog(message, encryptedText, encryptor.getEncryptorName());
        return encryptedText;
    }
}
