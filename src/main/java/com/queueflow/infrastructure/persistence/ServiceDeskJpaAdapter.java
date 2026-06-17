package com.queueflow.infrastructure.persistence;

import com.queueflow.domain.model.ServiceDesk;
import com.queueflow.domain.port.ServiceDeskRepositoryPort;
import com.queueflow.infrastructure.persistence.entity.ServiceDeskEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ServiceDeskJpaAdapter implements ServiceDeskRepositoryPort {

    private final ServiceDeskJpaRepository repository;

    public ServiceDeskJpaAdapter(ServiceDeskJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public ServiceDesk save(ServiceDesk serviceDesk) {

        ServiceDeskEntity savedEntity =
                repository.save(mapToEntity(serviceDesk));

        return mapToDomain(savedEntity);
    }

    @Override
    public Optional<ServiceDesk> findById(UUID id) {
        return repository.findById(id)
                .map(this::mapToDomain);
    }

    @Override
    public boolean existsByCode(String code) {
        return repository.existsByCode(code);
    }

    private ServiceDeskEntity mapToEntity(ServiceDesk serviceDesk){
        ServiceDeskEntity serviceDeskEntity = new ServiceDeskEntity();
        serviceDeskEntity.setId(serviceDesk.getId());
        serviceDeskEntity.setCode(serviceDesk.getCode());
        serviceDeskEntity.setActive(serviceDesk.getActive());
        serviceDeskEntity.setName(serviceDesk.getName());
        serviceDeskEntity.setDescription(serviceDesk.getDescription());

        return serviceDeskEntity;
    }

    private ServiceDesk mapToDomain(
            ServiceDeskEntity entity){

        ServiceDesk serviceDesk = new ServiceDesk();

        serviceDesk.setId(entity.getId());
        serviceDesk.setCode(entity.getCode());
        serviceDesk.setName(entity.getName());
        serviceDesk.setDescription(entity.getDescription());
        serviceDesk.setActive(entity.getActive());

        return serviceDesk;
    }
}
