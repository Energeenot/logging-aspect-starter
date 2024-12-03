package ru.Energeenot.logging_aspect_starter.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@ConditionalOnProperty(name = "metric.jpa-repositories.enabled", havingValue = "true")
@EnableJpaRepositories(basePackages = "ru.Energeenot.logging_aspect_starter.repository")
@EntityScan(basePackages = "ru.Energeenot.logging_aspect_starter.model")
public class StarterJpaAutoConfig {
}
