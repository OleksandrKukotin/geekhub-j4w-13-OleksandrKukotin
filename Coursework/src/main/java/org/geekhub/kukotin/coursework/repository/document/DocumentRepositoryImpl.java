package org.geekhub.kukotin.coursework.repository.document;

import org.geekhub.kukotin.coursework.dto.DocumentDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DocumentMapper mapper;

    public DocumentRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, DocumentMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public void addDocument(DocumentDTO document) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("docType", document.documentTypeId())
            .addValue("docTitle", document.documentTitle())
            .addValue("description", document.description())
            .addValue("publishingYear", document.publishingYear())
            .addValue("authorId", document.authorId())
            .addValue("countryId", document.countryId())
            .addValue("availableCopies", document.availableCopies())
            .addValue("lastUpdateTime", Timestamp.from(document.updated()))
            .addValue("updatedBy", document.updatedBy());
        String query = """
            insert into documents(document_type, document_title, description, publishing_year, author_id, country_id,
            available_copies, last_update_time, updated_by)
            values (:docType, :docTitle, :description, :publishingYear, :authorId, :countryId, :availableCopies,
            :lastUpdateTime, :updatedBy)
            """;
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void updateDocument(DocumentDTO updatedDocument) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("docId", updatedDocument.documentId())
            .addValue("docType", updatedDocument.documentTypeId())
            .addValue("docTitle", updatedDocument.documentTitle())
            .addValue("description", updatedDocument.description())
            .addValue("publishingYear", updatedDocument.publishingYear())
            .addValue("authorId", updatedDocument.authorId())
            .addValue("countryId", updatedDocument.countryId())
            .addValue("availableCopies", updatedDocument.availableCopies())
            .addValue("now", Timestamp.from(Instant.now()))
            .addValue("updatedBy", updatedDocument.updatedBy());
        String query = """
            update documents
            set document_type = :docType, document_title = :docTitle, description = description,
            publishing_year = :publishingYear, author_id = :authorId, country_id = :countryId,
            available_copies = :availableCopies, last_update_time = :now, updated_by = :updatedBy
            where document_id = :docId
            """;
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void removeDocument(DocumentDTO documentToRemove) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("docId", documentToRemove.documentId());
        String query = "delete from documents where document_id = :docId";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public List<DocumentDTO> getAllDocuments() {
        String query = "select * from documents";
        return jdbcTemplate.query(query, mapper);
    }
}
