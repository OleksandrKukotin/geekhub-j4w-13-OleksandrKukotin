package org.geekhub.kukotin.coursework.repository.author;

import org.geekhub.kukotin.coursework.dto.AuthorDTO;

import java.util.List;

public interface AuthorRepository {

    void addAuthor(AuthorDTO dto);

    void updateAuthor(AuthorDTO dto);

    void deleteAuthor(AuthorDTO dto);

    List<AuthorDTO> getAllAuthors();
}
