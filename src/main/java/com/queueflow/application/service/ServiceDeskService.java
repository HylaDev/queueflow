package com.queueflow.application.service;

import com.queueflow.domain.exception.ServiceDeskAlreadyExistsException;
import com.queueflow.domain.model.ServiceDesk;
import com.queueflow.domain.port.ServiceDeskRepositoryPort;
import com.queueflow.web.dto.response.ServiceDeskResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

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

        serviceDesk.setActive(true);
        serviceDesk.setCreatedAt(Instant.now());
        serviceDesk.setUpdatedAt(Instant.now());

        return repositoryPort.save(serviceDesk);
    }

    public List<ServiceDesk> getServiceDesks(Boolean active) {

        if(active == null) {

            return repositoryPort.findAll();
        }

        return repositoryPort.findAllByActive(active);
    }

    private void validateBusinessRules(
            ServiceDesk serviceDesk) {

        if (repositoryPort.existsByCode(
                serviceDesk.getCode())) {

            throw new ServiceDeskAlreadyExistsException(serviceDesk.getCode());
        }
    }

}
