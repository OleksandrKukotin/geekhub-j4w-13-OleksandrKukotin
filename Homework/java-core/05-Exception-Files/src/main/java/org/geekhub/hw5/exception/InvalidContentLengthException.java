package org.geekhub.hw5.exception;

import java.io.IOException;

public class InvalidContentLengthException extends IOException {

    public InvalidContentLengthException(String message) {
        super(message);
    }
}
