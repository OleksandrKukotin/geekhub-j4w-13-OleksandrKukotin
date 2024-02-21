package com.geekhub.hw15.encoding.cryptors.algorithms;

import com.geekhub.hw15.exception.WrongKeyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VigenereAlgorithmTest {

    private VigenereAlgorithm vigenereAlgorithm;

    @BeforeEach
    void setUp() {
        Assertions.assertThrows(WrongKeyException.class, () -> new VigenereAlgorithm("  "));
        vigenereAlgorithm = new VigenereAlgorithm("answer");
    }

    @Test
    void decryptionEncryptedMessage_shouldReturnMessage() {
        String message = "Lorem ipsum dolor sit amet... oh, is it a real devil in my room?";
        String encrypted = vigenereAlgorithm.encrypt(message);
        Assertions.assertEquals(message, vigenereAlgorithm.decrypt(encrypted));
    }
}
