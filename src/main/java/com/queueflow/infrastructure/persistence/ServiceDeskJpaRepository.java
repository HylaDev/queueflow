package com.queueflow.infrastructure.persistence;

import com.queueflow.infrastructure.persistence.entity.ServiceDeskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface ServiceDeskJpaRepository  extends
        JpaRepository<ServiceDeskEntity, UUID> {
    boolean existsByCode(String code);

}
