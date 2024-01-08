package org.geekhub.hw11.model;

import java.time.OffsetDateTime;

public record LogEntry(OffsetDateTime time, String originalMessage, String encryptedMessage, String algorithm) {

}
