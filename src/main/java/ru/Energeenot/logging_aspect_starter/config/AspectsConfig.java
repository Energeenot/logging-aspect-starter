package ru.Energeenot.logging_aspect_starter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.Energeenot.logging_aspect_starter.aspects.DataSourceErrorAspect;
import ru.Energeenot.logging_aspect_starter.kafka.MetricProducer;
import ru.Energeenot.logging_aspect_starter.repository.DataSourceErrorLogRepository;
import ru.Energeenot.logging_aspect_starter.service.ErrorLogService;

@Configuration
public class AspectsConfig {

    private final MetricProducer metricProducer;
    private final DataSourceErrorLogRepository errorLogRepository;

    @Autowired
    public AspectsConfig(MetricProducer metricProducer, DataSourceErrorLogRepository errorLogRepository) {
        this.metricProducer = metricProducer;
        this.errorLogRepository = errorLogRepository;
    }

    @Bean
    public ErrorLogService errorLogService() {
        return new ErrorLogService(errorLogRepository);
    }

    @Bean
    public DataSourceErrorAspect dataSourceErrorAspect() {
        return new DataSourceErrorAspect(errorLogService(), metricProducer);
    }
}
