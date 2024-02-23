package com.geekhub.hw15.encoding.cryptors;

import com.geekhub.hw15.encoding.EncodingAlgorithm;
import com.geekhub.hw15.encoding.cryptors.algorithms.CaesarAlgorithm;
import com.geekhub.hw15.encoding.cryptors.algorithms.OneWayEncodingAlgorithm;
import com.geekhub.hw15.encoding.cryptors.algorithms.VigenereAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class EncryptorFactoryTest {

    @Mock
    private CaesarAlgorithm mockCaesarAlgorithm;
    @Mock
    private VigenereAlgorithm mockVigenereAlgorithm;
    @Mock
    private OneWayEncodingAlgorithm mockOneWayEncodingAlgorithm;

    private EncryptorFactory encryptorFactory;

    @BeforeEach
    void setUp() {
        encryptorFactory = new EncryptorFactory(mockCaesarAlgorithm, mockVigenereAlgorithm, mockOneWayEncodingAlgorithm);
    }

    @Test
    void getEncryptor_returnsCaesarAlgorithm_forCaesarEncoding() {
        Encryptor encryptor = encryptorFactory.getEncryptor(EncodingAlgorithm.CAESAR);

        assertEquals(mockCaesarAlgorithm, encryptor);
    }

    @Test
    void getEncryptor_returnsVigenereAlgorithm_forVigenereEncoding() {
        Encryptor encryptor = encryptorFactory.getEncryptor(EncodingAlgorithm.VIGENERE);

        assertEquals(mockVigenereAlgorithm, encryptor);
    }

    @Test
    void getEncryptor_returnsOneWayEncodingAlgorithm_forSHA256Encoding() {
        Encryptor encryptor = encryptorFactory.getEncryptor(EncodingAlgorithm.SHA256);

        assertEquals(mockOneWayEncodingAlgorithm, encryptor);
    }

    @Test
    void getEncryptor_throwsException_forUnsupportedAlgorithm() {
        assertThrows(IllegalArgumentException.class, () -> {
            encryptorFactory.getEncryptor(EncodingAlgorithm.fromValue("something"));
        });
    }
}
