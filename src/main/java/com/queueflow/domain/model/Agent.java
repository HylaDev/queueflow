package com.queueflow.domain.model;

import java.time.Instant;
import java.util.UUID;

public class Agent {

    private UUID id;

    private String firstname;

    private String lastname;

    private String email;

    private String phone;

    private Boolean active;

    private Instant createdAt;

    private Instant updatedAt;
}