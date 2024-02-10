package com.geekhub.hw15.encoding.cryptors;

import com.geekhub.hw15.encoding.EncodingAlgorithm;
import com.geekhub.hw15.encoding.cryptors.algorithms.CaesarAlgorithm;
import com.geekhub.hw15.encoding.cryptors.algorithms.VigenereAlgorithm;

public class DecryptorFactory {

    private final CaesarAlgorithm caesarEncryption;
    private final VigenereAlgorithm vigenereEncryption;

    public DecryptorFactory(CaesarAlgorithm caesarEncryption,
                            VigenereAlgorithm vigenereEncryption) {
        this.caesarEncryption = caesarEncryption;
        this.vigenereEncryption = vigenereEncryption;
    }

    public Decryptor getDecryptor(EncodingAlgorithm type) {
        return switch (type) {
            case CAESAR -> caesarEncryption;
            case VIGENERE -> vigenereEncryption;
            default -> throw new IllegalArgumentException("Unsupported algorithm: " + type);
        };
    }
}
