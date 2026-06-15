package com.queueflow.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "queue_history")
public class QueueHistoryEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(name = "ticket_id")
    private TicketEntity ticket;

    @ManyToOne
    @JoinColumn(name = "counter_id")
    private CounterEntity counter;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private AgentEntity agent;

    @Column(nullable = false)
    private Instant startTime;

    @Column(nullable = false)
    private Instant endTime;

    @Column(nullable = false)
    private Long waitingTimeInSeconds;

    @Column(nullable = false)
    private Long serviceTimeInSeconds;
}