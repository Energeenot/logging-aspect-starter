package ru.Energeenot.logging_aspect_starter.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.Energeenot.logging_aspect_starter.aspects.DataSourceErrorAspect;
import ru.Energeenot.logging_aspect_starter.aspects.MetricAspect;
import ru.Energeenot.logging_aspect_starter.kafka.MetricProducer;
import ru.Energeenot.logging_aspect_starter.repository.DataSourceErrorLogRepository;
import ru.Energeenot.logging_aspect_starter.service.ErrorLogService;

@ComponentScan(basePackages = {"ru.Energeenot.logging_aspect_starter.config",
                                "ru.Energeenot.logging_aspect_starter.repository"})
@Configuration
@Slf4j
public class AspectsConfig {

    @Bean
    public ErrorLogService errorLogService(DataSourceErrorLogRepository errorLogRepository) {
        log.info("ErrorLogService has been initialized");
        return new ErrorLogService(errorLogRepository);
    }

    @Bean
    public DataSourceErrorAspect dataSourceErrorAspect(ErrorLogService errorLogService, @Qualifier("metricProducer") MetricProducer metricProducer) {
        log.info("DataSourceErrorAspect has been initialized");
        return new DataSourceErrorAspect(errorLogService, metricProducer);
    }

    @Bean
    public MetricAspect metricAspect(@Qualifier("metricProducer") MetricProducer metricProducer) {
        log.info("MetricAspect has been initialized");
        return new MetricAspect(metricProducer);
    }
}
