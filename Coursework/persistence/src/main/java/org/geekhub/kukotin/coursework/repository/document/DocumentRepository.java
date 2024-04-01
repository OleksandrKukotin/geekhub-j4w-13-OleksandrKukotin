package org.geekhub.kukotin.coursework.repository.document;

import dto.DocumentDTO;

import java.util.List;

public interface DocumentRepository {

    void addDocument(DocumentDTO dto);

    void updateDocument(DocumentDTO dto);

    void removeDocument(DocumentDTO dto);

    List<DocumentDTO> getAllDocuments();
}
