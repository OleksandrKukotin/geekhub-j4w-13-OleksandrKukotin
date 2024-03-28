package org.geekhub.kukotin.coursework.repository.documenttype;

import org.geekhub.kukotin.coursework.dto.DocumentTypeDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentTypeRepositoryImpl implements DocumentTypeRepository{

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DocumentTypeMapper mapper;

    public DocumentTypeRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, DocumentTypeMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public void addType(DocumentTypeDTO dto) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("typeName", dto.typeName());
        String query = "insert into document_types(type_name) values (:typeName)";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void updateType(DocumentTypeDTO dto) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("typeId", dto.typeId())
            .addValue("typeName", dto.typeName());
        String query = "update document_types set type_name = :typeName where type_id = :typeId";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void deleteType(DocumentTypeDTO dto) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("typeId", dto.typeId());
        String query = "delete from document_types where type_id = :typeId";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public List<DocumentTypeDTO> getAllTypes() {
        String query = "select * from document_types";
        return jdbcTemplate.query(query, mapper);
    }
}
