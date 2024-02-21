package com.geekhub.hw15.encoding.cryptors.algorithms;

import com.geekhub.hw15.exception.WrongKeyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CaesarAlgorithmTest {

    private CaesarAlgorithm caesarAlgorithm;

    @BeforeEach
    void setUp() {
        Assertions.assertThrows(WrongKeyException.class, () -> new CaesarAlgorithm(0));
        caesarAlgorithm = new CaesarAlgorithm(5);
    }

    @Test
    void decodingEncodedMessage_shouldReturnMessage() {
        String message = "test message";
        String encoded = caesarAlgorithm.encrypt(message);
        Assertions.assertEquals(message, caesarAlgorithm.decrypt(encoded));
    }

    @Test
    void operationWithBlankMessage_shouldReturnEmptyString() {
        String message = "   ";
        Assertions.assertEquals("", caesarAlgorithm.encrypt(message));
    }
}
