package ru.Energeenot.logging_aspect_starter.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.Energeenot.logging_aspect_starter.kafka.MetricProducer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties
public class TrackingKafkaConfig {

    @Value("${metric.kafka.bootstrap.server}")
    private String servers;

    @Bean("metric")
    @ConditionalOnProperty(value = "metric.kafka.enabled",
            havingValue = "true",
            matchIfMissing = true)
    public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    @ConditionalOnProperty(value = "metric.kafka.enabled",
            havingValue = "true",
            matchIfMissing = true)
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);

    }

    @Bean(name = "metricProducer")
    @ConditionalOnProperty(value = "kafka.enabled",
            havingValue = "true",
            matchIfMissing = true)
    public MetricProducer metricProducer(@Qualifier("metric") KafkaTemplate<String, Object> kafkaTemplate) {
        return new MetricProducer(kafkaTemplate);
    }

}
