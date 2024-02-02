package org.geekhub.hw11.repository;

import org.geekhub.hw11.model.LogEntry;

import java.util.List;

public interface Repository {

    void saveLogEntry(LogEntry entry);

    List<LogEntry> getLogs();
}
