package com.geekhub.hw15.encoding;

import com.geekhub.hw15.exception.EncodingAlgorithmException;

public enum EncodingAlgorithm {

    CAESAR("Caesar cipher"),
    VIGENERE("Vigenere cipher"),
    SHA256("One-way algorithm"),
    PLACEHOLDER("Place for a new algorithm");

    private final String value;

    EncodingAlgorithm(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EncodingAlgorithm fromValue(String value) {
        for (EncodingAlgorithm algorithm : values()) {
            if (algorithm.value.equals(value)) {
                return algorithm;
            }
        }
        throw new EncodingAlgorithmException("Invalid algorithm value: " + value);
    }
}
