package org.geekhub.kukotin.coursework.controllers;

import dto.DocumentTypeDTO;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.DocumentTypeService;

import java.util.List;

@RestController
@RequestMapping("/document-types")
@Import(DocumentTypeService.class)
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @PostMapping
    public ResponseEntity<Void> addDocumentType(@RequestBody DocumentTypeDTO documentTypeDTO) {
        documentTypeService.addDocumentType(documentTypeDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateDocumentType(@RequestBody DocumentTypeDTO documentTypeDTO) {
        documentTypeService.updateDocumentType(documentTypeDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteDocumentType(@RequestBody DocumentTypeDTO documentTypeDTO) {
        documentTypeService.deleteDocumentType(documentTypeDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<DocumentTypeDTO>> getAllDocumentTypes() {
        List<DocumentTypeDTO> documentTypes = documentTypeService.getAllDocumentTypes();
        return ResponseEntity.ok(documentTypes);
    }
}
