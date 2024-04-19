package org.geekhub.kukotin.coursework.repository.document;

import org.geekhub.kukotin.coursework.service.document.Document;

import java.util.List;

public interface DocumentRepository {

    void addDocument(Document document);

    void updateDocument(Document document);

    void removeDocument(Document document);

    List<Document> getAllDocuments();
}
