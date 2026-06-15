package com.queueflow.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "counters")
public class CounterEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    private String location;

    @Column(nullable = false)
    private Boolean active;

    private Instant createdAt;

    private Instant updatedAt;

    @OneToMany(mappedBy = "counter")
    private List<TicketEntity> tickets;
}