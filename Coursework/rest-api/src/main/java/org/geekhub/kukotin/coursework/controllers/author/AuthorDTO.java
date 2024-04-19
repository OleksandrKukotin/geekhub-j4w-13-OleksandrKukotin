package org.geekhub.kukotin.coursework.controllers.author;

import org.springframework.lang.NonNull;

public record AuthorDTO(

    @NonNull int authorId,
    @NonNull String name,
    @NonNull String surname
) {
}
