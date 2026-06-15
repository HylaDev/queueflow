package com.queueflow.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "agents")
public class AgentEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    @Column(nullable = false)
    private Boolean active;

    private Instant createdAt;

    private Instant updatedAt;

    @OneToOne
    @JoinColumn(name = "counter_id")
    private CounterEntity counter;
}