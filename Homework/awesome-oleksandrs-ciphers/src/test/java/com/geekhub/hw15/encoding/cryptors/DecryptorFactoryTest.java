package com.geekhub.hw15.encoding.cryptors;

import com.geekhub.hw15.encoding.EncodingAlgorithm;
import com.geekhub.hw15.encoding.cryptors.algorithms.CaesarAlgorithm;
import com.geekhub.hw15.encoding.cryptors.algorithms.VigenereAlgorithm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DecryptorFactoryTest {

    @Mock
    private CaesarAlgorithm mockCaesarAlgorithm;

    @Mock
    private VigenereAlgorithm mockVigenereAlgorithm;

    private DecryptorFactory decryptorFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        decryptorFactory = new DecryptorFactory(mockCaesarAlgorithm, mockVigenereAlgorithm);
    }

    @Test
    void getDecryptor_returnsCaesarAlgorithm_forCaesarEncoding() {
        // Arrange
        DecryptorFactory decryptorFactory = new DecryptorFactory(mockCaesarAlgorithm, mockVigenereAlgorithm);

        // Act
        Decryptor decryptor = decryptorFactory.getDecryptor(EncodingAlgorithm.CAESAR);

        // Assert
        assertEquals(mockCaesarAlgorithm, decryptor);
    }

    @Test
    void getDecryptor_returnsVigenereAlgorithm_forVigenereEncoding() {
        DecryptorFactory decryptorFactory = new DecryptorFactory(mockCaesarAlgorithm, mockVigenereAlgorithm);

        Decryptor decryptor = decryptorFactory.getDecryptor(EncodingAlgorithm.VIGENERE);

        assertEquals(mockVigenereAlgorithm, decryptor);
    }

    @Test
    void getDecryptor_throwsException_forUnsupportedAlgorithm() {
        DecryptorFactory decryptorFactory = new DecryptorFactory(mockCaesarAlgorithm, mockVigenereAlgorithm);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            decryptorFactory.getDecryptor(EncodingAlgorithm.PLACEHOLDER);
        });
    }
}
