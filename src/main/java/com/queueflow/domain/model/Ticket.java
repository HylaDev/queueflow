package com.queueflow.domain.model;

import java.time.Instant;
import java.util.UUID;

public class Ticket {

    private UUID id;

    private String number;

    private TicketStatus status;

    private Instant createdAt;

    private Instant calledAt;

    private Instant completedAt;

    private Instant cancelledAt;

    private ServiceDesk serviceDesk;

    private Counter counter;
}