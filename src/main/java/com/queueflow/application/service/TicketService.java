package com.queueflow.application.service;

import com.queueflow.domain.model.ServiceDesk;
import com.queueflow.domain.model.Ticket;
import com.queueflow.domain.port.TicketRepositoryPort;
import org.springframework.stereotype.Component;

@Component
public class TicketService {

    private final TicketRepositoryPort ticketRepository;

    public TicketService(
            TicketRepositoryPort ticketRepository) {

        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(
            ServiceDesk serviceDesk) {

        Ticket ticket = new Ticket();

        return ticketRepository.save(ticket);
    }
}