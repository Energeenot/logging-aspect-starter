package ru.Energeenot.logging_aspect_starter.util;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DatabaseSchemaValidator {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseSchemaValidator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void validateSchema() {
        String tableCheckQuery = "SELECT to_regclass('data_source_error_log');";
        log.info("Check if there is a data_source_error_log table in the schema");
        String result = jdbcTemplate.queryForObject(tableCheckQuery, String.class);
        if (result == null) {
            log.info("data_source_error_log table is not found in the schema, creating a new one");
            String createTableQuery = "CREATE TABLE data_source_error_log (" +
                    "id BIGSERIAL PRIMARY KEY, " +
                    "stack_trace TEXT, " +
                    "message VARCHAR(255), " +
                    "method_signature VARCHAR(255)" +
                    ");";
            jdbcTemplate.execute(createTableQuery);
        }
    }
}
