package com.queueflow.infrastructure.persistence;

import com.queueflow.domain.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketJpaRepository
        extends JpaRepository<Ticket, UUID> {
}