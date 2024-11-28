package ru.Energeenot.logging_aspect_starter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Energeenot.logging_aspect_starter.model.DataSourceErrorLog;
import ru.Energeenot.logging_aspect_starter.repository.DataSourceErrorLogRepository;

@Service
public class ErrorLogService {

    private final DataSourceErrorLogRepository errorLogRepository;

    @Autowired
    public ErrorLogService(DataSourceErrorLogRepository errorLogRepository) {
        this.errorLogRepository = errorLogRepository;
    }

    public void saveErrorLog(DataSourceErrorLog log) {
        errorLogRepository.save(log);
    }
}
