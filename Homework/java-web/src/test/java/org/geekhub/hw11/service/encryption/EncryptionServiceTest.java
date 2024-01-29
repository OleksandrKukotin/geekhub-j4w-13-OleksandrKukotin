package org.geekhub.hw11.service.encryption;

import org.geekhub.hw11.service.logging.LoggingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EncryptionServiceTest {

    @Mock
    private LoggingService logger;

    private final CaesarCipherEncryptor caesarCipherEncryptor = new CaesarCipherEncryptor(5);
    private EncryptionService caesarEncryptionService;
    private final VigenereCipherEncryptor vigenereCipherEncryptor = new VigenereCipherEncryptor("world");
    private EncryptionService vigenereEncryptionService;

    @BeforeEach
    void setUp() {
        caesarEncryptionService = new EncryptionService(logger, caesarCipherEncryptor);
        vigenereEncryptionService = new EncryptionService(logger, vigenereCipherEncryptor);
    }

    @Test
    void encrypt_UsingCaesarCipher_ShouldReturnExpectedText() {
        // all messages/expects were taken from the logs.csv file
        String message = "Hello World!";
        String expected = "Mjqqt Btwqi!";

        Assertions.assertEquals(expected, caesarEncryptionService.encrypt(message));
    }

    @Test
    void encrypt_UsingVigenereCipher_ShouldReturnExpectedText() {
        String message = "Incredible message for testing my app";
        String expected = "Khzinfcycn oypjjiy cfa vypkrpa jp jrj";

        Assertions.assertEquals(expected, vigenereEncryptionService.encrypt(message));
    }
}
