package org.geekhub.kukotin.coursework.controllers.dto;

import org.springframework.lang.NonNull;

public record AuthorDTO(

    @NonNull long authorId,
    @NonNull String name,
    @NonNull String surname
) {
}
