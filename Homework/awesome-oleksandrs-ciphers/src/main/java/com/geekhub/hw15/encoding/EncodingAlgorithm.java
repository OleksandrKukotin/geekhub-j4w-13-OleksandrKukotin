package com.geekhub.hw15.encoding;

public enum EncodingAlgorithm {

    CAESAR("Caesar cipher"),
    VIGENERE("Vigenere cipher"),
    SHA256("One-way algorithm");

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
        throw new IllegalArgumentException("Invalid algorithm value: " + value);
    }
}
