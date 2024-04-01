package org.geekhub.kukotin.coursework.repository.documenttype;

import dto.DocumentTypeDTO;

import java.util.List;

public interface DocumentTypeRepository {

    void addType(DocumentTypeDTO dto);

    void updateType(DocumentTypeDTO dto);

    void deleteType(DocumentTypeDTO dto);

    List<DocumentTypeDTO> getAllTypes();
}
