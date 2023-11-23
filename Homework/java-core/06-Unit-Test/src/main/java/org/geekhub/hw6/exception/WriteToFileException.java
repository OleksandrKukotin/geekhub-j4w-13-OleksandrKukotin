package org.geekhub.hw6.exception;

public class WriteToFileException extends RuntimeException {

    public WriteToFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
