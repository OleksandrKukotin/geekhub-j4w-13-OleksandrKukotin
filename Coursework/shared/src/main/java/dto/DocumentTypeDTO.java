package dto;

import org.springframework.lang.NonNull;

public record DocumentTypeDTO(

    @NonNull long typeId,
    @NonNull String typeName
) {
}
