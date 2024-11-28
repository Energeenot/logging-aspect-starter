package ru.Energeenot.logging_aspect_starter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.Energeenot.logging_aspect_starter.util.DatabaseSchemaValidator;

@Configuration
@EnableJpaRepositories
public class DatabaseSchemaConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate();
    }

    @Bean
    public DatabaseSchemaValidator databaseSchemaValidator() {
        return new DatabaseSchemaValidator(jdbcTemplate());
    }
}
