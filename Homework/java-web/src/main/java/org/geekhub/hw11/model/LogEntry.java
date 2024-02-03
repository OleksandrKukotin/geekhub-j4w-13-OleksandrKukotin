package org.geekhub.hw11.model;

import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;

public record LogEntry(Instant time, String input, String algorithm, String encrypted, @Value("${activeUser.id}") int userId) {
}
