package org.geekhub.hw11.service.encryption;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VigenereCipherEncryptorTest {

    private VigenereCipherEncryptor encryptor;

    @BeforeEach
    void setUp() {
        encryptor = new VigenereCipherEncryptor("world");
    }

    @Test
    void encrypt() {
        String message = "Incredible message for testing my app";
        String expected = "Khzinfcycn oypjjiy cfa vypkrpa jp jrj";

        Assertions.assertEquals(expected, encryptor.encrypt(message));
    }

    @Test
    void getEncryptorName() {
        Assertions.assertEquals("Vigenere cipher", encryptor.getEncryptorName());
    }
}
