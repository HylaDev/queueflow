package com.queueflow.application.service;

import com.queueflow.domain.exception.ServiceDeskAlreadyExistsException;
import com.queueflow.domain.model.ServiceDesk;
import com.queueflow.domain.port.ServiceDeskRepositoryPort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ServiceDeskService {

    private final ServiceDeskRepositoryPort repositoryPort;

    public ServiceDeskService(
            ServiceDeskRepositoryPort repositoryPort) {

        this.repositoryPort = repositoryPort;
    }

    public ServiceDesk create(
            ServiceDesk serviceDesk) {

        validateBusinessRules(serviceDesk);

        return repositoryPort.save(serviceDesk);
    }

    private void validateBusinessRules(
            ServiceDesk serviceDesk) {

        if (repositoryPort.existsByCode(
                serviceDesk.getCode())) {

            throw new ServiceDeskAlreadyExistsException(
                    serviceDesk.getCode());
        }
    }
}
