package org.geekhub.kukotin.coursework.repository.author;

import dto.AuthorDTO;
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
    public void addAuthor(AuthorDTO dto) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("authorName", dto.name())
            .addValue("authorSurname", dto.surname());
        String query = "insert into authors(author_name, author_surname) values(:authorName, :authorSurname)";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void updateAuthor(AuthorDTO dto) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("authorId", dto.authorId())
            .addValue("authorName", dto.name())
            .addValue("authorSurname", dto.surname());
        String query = """
            update authors set author_name = :authorName, author_surname = :authorSurname
            where author_id = :authorId
            """;
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void deleteAuthor(AuthorDTO dto) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("authorId", dto.authorId());
        String query = "delete from authors where author_id  = :authorId";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        String query = "select * from authors";
        return jdbcTemplate.query(query, mapper);
    }
}
