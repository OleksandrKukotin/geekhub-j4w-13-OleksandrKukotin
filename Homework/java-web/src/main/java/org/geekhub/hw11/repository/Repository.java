package org.geekhub.hw11.repository;

import org.geekhub.hw11.model.LogEntry;

import java.util.List;

public interface Repository {

    void save(List<LogEntry> log);

    List<LogEntry> getLogs();
}
