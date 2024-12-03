package ru.Energeenot.logging_aspect_starter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.Energeenot.logging_aspect_starter.util.DatabaseSchemaValidator;

@Configuration
public class DatabaseSchemaConfig {

    @Bean
    @ConditionalOnMissingBean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate();
    }

    @Bean
    public DatabaseSchemaValidator databaseSchemaValidator() {
        return new DatabaseSchemaValidator(jdbcTemplate());
    }
}
