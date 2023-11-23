package org.geekhub.hw6.exception;

public class ApiExecutionException extends RuntimeException {

    public ApiExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
