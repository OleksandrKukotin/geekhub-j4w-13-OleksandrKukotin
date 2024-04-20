package org.geekhub.kukotin.coursework.service.author;

import java.util.List;

public interface AuthorRepository {

    void save(Author author);

    Author findAuthorById(int authorId);

    List<Author> findAllAuthors();

    void updateAuthor(Author author);

    void deleteAuthor(Author author);
}
