package org.geekhub.hw11.service.encryption;

import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    private final Encryptor encryptor;

    public EncryptionService(Encryptor encryptor) {
        this.encryptor = encryptor;
    }

    public String encrypt(String message) {
        return encryptor.encrypt(message);
    }
}
