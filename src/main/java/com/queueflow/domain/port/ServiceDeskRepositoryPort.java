package com.queueflow.domain.port;

import com.queueflow.domain.model.ServiceDesk;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceDeskRepositoryPort {
    ServiceDesk save(ServiceDesk serviceDesk);
    Optional<ServiceDesk> findById(UUID id);

    List<ServiceDesk> findAll();

    List<ServiceDesk> findAllByActive(Boolean active);

    boolean existsByCode(String code);
}
