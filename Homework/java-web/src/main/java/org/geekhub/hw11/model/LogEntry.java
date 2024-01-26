package org.geekhub.hw11.model;

import java.time.Instant;

public record LogEntry(Instant time, String input, String algorithm, String encrypted) {
    public String stringForOutput() {
        return String.format("%s - Message '%s' was encrypted via %s into '%s'", time.toString(), input, algorithm, encrypted);
    }
}
