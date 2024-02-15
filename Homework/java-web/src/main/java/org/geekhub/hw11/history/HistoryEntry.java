package org.geekhub.hw11.history;

import com.geekhub.hw15.encoding.EncodingAlgorithm;
import com.geekhub.hw15.encoding.EncodingOperation;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.Instant;

public record HistoryEntry(
    @Nullable Integer entryId,
    @NonNull Instant creatingTime,
    @NonNull String message,
    @NonNull String encrypted,
    @NonNull EncodingAlgorithm algorithm,
    @NonNull int userId,
    @NonNull EncodingOperation operation
) {
}
