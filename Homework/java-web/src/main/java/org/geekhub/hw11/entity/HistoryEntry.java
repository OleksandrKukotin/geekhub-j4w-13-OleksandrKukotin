package org.geekhub.hw11.entity;

import org.springframework.lang.NonNull;

import java.time.Instant;

public record HistoryEntry(
    Integer entryId,
    @NonNull Instant createTime,
    @NonNull String message,
    @NonNull String encrypted,
    @NonNull String algorithm,
    @NonNull Integer userId,
    @NonNull String operation
) {
}
