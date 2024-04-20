package org.geekhub.kukotin.coursework.service.document;

import java.util.List;

public interface DocumentRepository {

    void save(Document document);

    void updateDocument(Document document);

    void removeDocument(Document document);

    List<Document> findAllDocuments();
}
