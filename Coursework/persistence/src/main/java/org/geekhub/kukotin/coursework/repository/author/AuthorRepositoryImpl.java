package org.geekhub.kukotin.coursework.repository.author;

import org.geekhub.kukotin.coursework.service.author.Author;
import org.geekhub.kukotin.coursework.service.author.AuthorRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final AuthorMapper mapper;

    public AuthorRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, AuthorMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public void save(Author author) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("authorName", author.getAuthorName())
            .addValue("authorSurname", author.getAuthorSurname());
        String query = "insert into authors(author_name, author_surname) values(:authorName, :authorSurname)";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public Author findAuthorById(int authorId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("authorId", authorId);
        String query = "select * from authors where author_id = :authorId";
        return jdbcTemplate.queryForObject(query, parameterSource, mapper);
    }

    @Override
    public List<Author> findAllAuthors() {
        return jdbcTemplate.query("select * from authors", mapper);
    }

    @Override
    public void updateAuthor(Author author) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("authorId", author.getAuthorId())
            .addValue("authorName", author.getAuthorName())
            .addValue("authorSurname", author.getAuthorSurname());
        String query = """
            update authors set author_name = :authorName, author_surname = :authorSurname
            where author_id = :authorId
            """;
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void deleteAuthor(Author author) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("authorId", author.getAuthorId());
        String query = "delete from authors where author_id  = :authorId";
        jdbcTemplate.update(query, parameterSource);
    }
}
