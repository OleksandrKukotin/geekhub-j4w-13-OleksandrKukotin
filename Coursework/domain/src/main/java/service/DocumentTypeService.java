package service;

import dto.DocumentTypeDTO;
import org.geekhub.kukotin.coursework.repository.documenttype.DocumentTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;

    public DocumentTypeService(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public void addDocumentType(DocumentTypeDTO dto) {
        documentTypeRepository.addType(dto);
    }

    public void updateDocumentType(DocumentTypeDTO dto) {
        documentTypeRepository.updateType(dto);
    }

    public void deleteDocumentType(DocumentTypeDTO dto) {
        documentTypeRepository.deleteType(dto);
    }

    public List<DocumentTypeDTO> getAllDocumentTypes() {
        return documentTypeRepository.getAllTypes();
    }
}
