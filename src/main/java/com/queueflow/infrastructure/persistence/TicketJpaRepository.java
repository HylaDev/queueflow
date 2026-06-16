package com.queueflow.infrastructure.persistence;

import com.queueflow.domain.model.Ticket;
import com.queueflow.infrastructure.persistence.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface TicketJpaRepository
        extends JpaRepository<TicketEntity, UUID> {
}