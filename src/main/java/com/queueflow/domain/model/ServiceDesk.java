package com.queueflow.domain.model;

import java.time.Instant;
import java.util.UUID;

public class ServiceDesk {
    private UUID id;
    private String code;
    private String name;
    private String description;
    private Boolean active;
    private Instant createdAt;
    private Instant updatedAt;

}
