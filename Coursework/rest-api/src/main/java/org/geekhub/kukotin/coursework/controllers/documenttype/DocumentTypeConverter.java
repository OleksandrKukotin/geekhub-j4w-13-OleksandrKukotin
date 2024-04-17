package org.geekhub.kukotin.coursework.controllers.documenttype;

import org.geekhub.kukotin.coursework.controllers.country.CountryConverter;
import org.geekhub.kukotin.coursework.service.documenttype.DocumentType;

import java.util.List;
import java.util.stream.Collectors;

public class DocumentTypeConverter {

    private DocumentTypeConverter(){
    }

    public static DocumentTypeDTO toDto(DocumentType entity) {
        return new DocumentTypeDTO(entity.getTypeId(), entity.getTypeName());
    }

    public static DocumentType fromDto(DocumentTypeDTO dto) {
        return new DocumentType(dto.typeId(), dto.typeName());
    }

    public static List<DocumentTypeDTO> toDtos(List<DocumentType> entities) {
        return entities.stream()
            .map(DocumentTypeConverter::toDto)
            .collect(Collectors.toList());
    }
}
