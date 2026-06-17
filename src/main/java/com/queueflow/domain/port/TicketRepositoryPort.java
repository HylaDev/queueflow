package com.queueflow.domain.port;

import com.queueflow.domain.model.Ticket;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepositoryPort {
    Ticket save(Ticket ticket);
    Optional<Ticket> findById(UUID id);
    boolean existsByCode(String code);

}