package com.queueflow.domain.model;

import java.time.Instant;
import java.util.UUID;

public class QueueHistory {

    private UUID id;

    private Ticket ticket;

    private Counter counter;

    private Agent agent;

    private Instant startTime;

    private Instant endTime;

    private Long waitingTimeInSeconds;

    private Long serviceTimeInSeconds;
}