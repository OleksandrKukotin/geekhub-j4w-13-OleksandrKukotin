package com.geekhub.hw15.encoding;

import com.geekhub.hw15.encoding.cryptors.DecryptorFactory;
import com.geekhub.hw15.encoding.cryptors.EncryptorFactory;

public class EncodingService {

    private final EncryptorFactory encryptorFactory;
    private final DecryptorFactory decryptorFactory;

    public EncodingService(EncryptorFactory encryptorFactory, DecryptorFactory decryptorFactory) {
        this.encryptorFactory = encryptorFactory;
        this.decryptorFactory = decryptorFactory;
    }

    public String encode(EncodingAlgorithm algorithm,
                         EncodingOperation operation,
                         String message) {
        return switch (operation) {
            case ENCRYPT -> encryptorFactory.getEncryptor(algorithm).encrypt(message);
            case DECRYPT -> decryptorFactory.getDecryptor(algorithm).decrypt(message);
        };
    }
}
