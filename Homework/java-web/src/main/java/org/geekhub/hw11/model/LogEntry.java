package org.geekhub.hw11.model;

import java.time.OffsetDateTime;

public record LogEntry(OffsetDateTime time, String input, String encrypted, String algorithm) {

    public String stringForLogFile() {
        return String.format("%tc ._. '%s' ._. %s ._. '%s'", time, input, algorithm, encrypted);
    }

    public String stringForOutput() {
        return String.format("%tc - Message '%s' was encrypted via %s into '%s'", time, input, algorithm, encrypted);
    }
}
