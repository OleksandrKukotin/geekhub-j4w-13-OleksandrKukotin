package org.geekhub.hw11.exception;

public class PropertiesNotFoundException extends RuntimeException {

    public PropertiesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
