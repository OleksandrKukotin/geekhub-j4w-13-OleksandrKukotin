package org.geekhub.hw5.exception;

import java.io.IOException;

public class ContentLengthNotKnownException extends IOException {

    public ContentLengthNotKnownException(String message) {
        super(message);
    }
}
