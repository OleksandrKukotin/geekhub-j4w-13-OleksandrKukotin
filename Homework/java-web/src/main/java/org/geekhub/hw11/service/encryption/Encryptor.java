package org.geekhub.hw11.service.encryption;

public interface Encryptor {

    String encrypt(String originalText);

    String getEncryptorName();
}
