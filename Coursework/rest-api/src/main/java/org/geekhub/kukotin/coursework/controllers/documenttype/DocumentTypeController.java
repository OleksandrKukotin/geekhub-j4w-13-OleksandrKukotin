package org.geekhub.kukotin.coursework.controllers.documenttype;

import org.geekhub.kukotin.coursework.service.documenttype.DocumentTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.geekhub.kukotin.coursework.controllers.documenttype.DocumentTypeConverter.fromDto;
import static org.geekhub.kukotin.coursework.controllers.documenttype.DocumentTypeConverter.toDtos;

@RestController
@RequestMapping("/document-types")
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addDocumentType(@RequestBody DocumentTypeDTO documentTypeDTO) {
        documentTypeService.addDocumentType(fromDto(documentTypeDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateDocumentType(@RequestBody DocumentTypeDTO documentTypeDTO) {
        documentTypeService.updateDocumentType(fromDto(documentTypeDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public void deleteDocumentType(@RequestBody DocumentTypeDTO documentTypeDTO) {
        documentTypeService.deleteDocumentType(fromDto(documentTypeDTO));
    }

    @GetMapping
    public ResponseEntity<List<DocumentTypeDTO>> getAllDocumentTypes() {
        return ResponseEntity.ok(toDtos(documentTypeService.getAllDocumentTypes()));
    }
}
