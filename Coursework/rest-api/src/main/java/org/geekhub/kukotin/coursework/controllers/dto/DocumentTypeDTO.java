package org.geekhub.kukotin.coursework.controllers.dto;

import org.springframework.lang.NonNull;

public record DocumentTypeDTO(

    @NonNull long typeId,
    @NonNull String typeName
) {
}
