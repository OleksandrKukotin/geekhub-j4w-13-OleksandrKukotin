package com.geekhub.hw15.encoding.cryptors;

import com.geekhub.hw15.encoding.EncodingAlgorithm;
import com.geekhub.hw15.encoding.cryptors.algorithms.CaesarAlgorithm;
import com.geekhub.hw15.encoding.cryptors.algorithms.OneWayEncodingAlgorithm;
import com.geekhub.hw15.encoding.cryptors.algorithms.VigenereAlgorithm;

public class EncryptorFactory {

    private final CaesarAlgorithm caesarEncryption;
    private final VigenereAlgorithm vigenereEncryption;
    private final OneWayEncodingAlgorithm oneWayEncodingAlgorithm;

    public EncryptorFactory(CaesarAlgorithm caesarEncryption,
                            VigenereAlgorithm vigenereEncryption,
                            OneWayEncodingAlgorithm oneWayEncodingAlgorithm) {
        this.caesarEncryption = caesarEncryption;
        this.vigenereEncryption = vigenereEncryption;
        this.oneWayEncodingAlgorithm = oneWayEncodingAlgorithm;
    }

    public Encryptor getEncryptor(EncodingAlgorithm type) {
        return switch (type) {
            case CAESAR -> caesarEncryption;
            case VIGENERE -> vigenereEncryption;
            case SHA256 -> oneWayEncodingAlgorithm;
        };
    }
}
