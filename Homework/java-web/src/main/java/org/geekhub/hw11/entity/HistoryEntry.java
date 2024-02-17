package org.geekhub.hw11.entity;

import com.geekhub.hw15.encoding.EncodingAlgorithm;
import com.geekhub.hw15.encoding.EncodingOperation;
import org.springframework.lang.NonNull;

import java.time.Instant;

public record HistoryEntry(
    Integer entryId,
    @NonNull Instant creatingTime,
    @NonNull String message,
    @NonNull String encrypted,
    @NonNull EncodingAlgorithm algorithm,
    @NonNull Long userId,
    @NonNull EncodingOperation operation
) {
}
