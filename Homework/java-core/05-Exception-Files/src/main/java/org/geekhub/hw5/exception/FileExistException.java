package org.geekhub.hw5.exception;

import java.io.IOException;

public class FileExistException extends IOException {

    public FileExistException(String message) {
        super(message);
    }
}
