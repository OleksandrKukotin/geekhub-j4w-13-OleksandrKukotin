package org.geekhub.kukotin.coursework.controllers.documenttype;

import org.springframework.lang.NonNull;

public record DocumentTypeDTO(

    @NonNull int typeId,
    @NonNull String typeName
) {
}
