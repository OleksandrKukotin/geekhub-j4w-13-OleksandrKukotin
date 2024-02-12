package org.geekhub.hw11.history;

import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;

public record HistoryEntry(int entryId,
                           Instant time,
                           String input,
                           String algorithm,
                           String encrypted,
                           @Value("${activeUser.id}") int userId) {
}
