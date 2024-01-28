package org.geekhub.hw11.model;

import java.time.Instant;

public record LogEntry(Instant time, String input, String algorithm, String encrypted) {
}
