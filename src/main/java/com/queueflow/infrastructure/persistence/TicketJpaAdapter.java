package com.queueflow.infrastructure.persistence;

import com.queueflow.domain.model.Ticket;
import com.queueflow.domain.port.TicketRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class TicketJpaAdapter
        implements TicketRepositoryPort {

    private final TicketJpaRepository repository;

    public TicketJpaAdapter(
            TicketJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Ticket save(Ticket ticket) {

        return null;
    }

    @Override
    public Optional<Ticket> findById(UUID id) {

        return Optional.empty();
    }

    @Override
    public boolean existsByCode(String code) {
        return false;
    }
}