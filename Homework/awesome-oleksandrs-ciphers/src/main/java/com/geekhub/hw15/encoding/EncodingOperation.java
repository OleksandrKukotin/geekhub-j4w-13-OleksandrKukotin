package com.geekhub.hw15.encoding;

public enum EncodingOperation {

    ENCRYPT("Encryption"),
    DECRYPT("Decryption");

    private final String value;

    EncodingOperation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EncodingOperation fromValue(String value) {
        for (EncodingOperation operation : values()) {
            if (operation.value.equals(value)) {
                return operation;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }
}
