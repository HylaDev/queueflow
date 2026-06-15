# QueueFlow

QueueFlow is a distributed queue management platform designed for public and private service organizations.

The platform allows citizens, customers, or visitors to take tickets, monitor queue progression, and be served efficiently through dedicated service desks and counters.

This project is primarily built to explore modern backend engineering practices including Hexagonal Architecture, Domain-Driven Design principles, event-driven communication, and asynchronous processing.

---

## Features

### Ticket Management

* Create service tickets
* Track ticket status
* Cancel tickets
* Retrieve ticket information

### Queue Management

* Call next ticket
* Start service processing
* Complete service processing
* Handle no-show tickets

### Service Desk Management

* Manage multiple service desks
* Dedicated queue per service
* Counter assignment

### Statistics & Monitoring

* Average waiting time
* Average processing time
* Tickets per day
* Queue performance indicators

---

## Architecture

The application follows a Hexagonal Architecture (Ports & Adapters) approach.

```text
Web Layer
     │
     ▼
Application Services
     │
     ▼
Domain Ports
     │
     ▼
Infrastructure Adapters
     │
     ▼
PostgreSQL / Kafka / RabbitMQ
```

Project structure:

```text
com.queueflow

├── domain
│   ├── model
│   ├── port
│   ├── enums
│   └── exception
│
├── application
│   └── service
│
├── infrastructure
│   ├── persistence
│   ├── kafka
│   ├── rabbitmq
│   ├── security
│   └── configuration
│
└── web
    ├── controller
    ├── dto
    └── exception
```

---

## Technology Stack

### Backend

* Java 21
* Spring Boot 4
* Spring Web
* Spring Data JPA
* Spring Security

### Database

* PostgreSQL

### Messaging

* Apache Kafka
* RabbitMQ

### Testing

* JUnit 5
* Mockito
* Testcontainers

### DevOps

* Docker
* Docker Compose

---

## Domain Model

Core business entities:

* Ticket
* ServiceDesk
* Counter
* Agent
* QueueHistory

Ticket lifecycle:

```text
WAITING
    │
    ▼
CALLED
    │
    ▼
IN_PROGRESS
    │
    ▼
COMPLETED
```

Alternative states:

```text
NO_SHOW
CANCELED
```

---

## Security

Authentication and authorization are based on JWT tokens.

Supported roles:

* ROLE_USER
* ROLE_AGENT
* ROLE_MANAGER
* ROLE_ADMIN

---

## Future Improvements

* Real-time notifications
* SMS integration
* Email notifications
* Monitoring dashboard
* Prometheus & Grafana integration
* Kubernetes deployment
* Event sourcing exploration

---

## Learning Objectives

This project aims to demonstrate:

* Object-Oriented Programming
* SOLID Principles
* Hexagonal Architecture
* Domain-Driven Design fundamentals
* Event-Driven Architecture
* Asynchronous Processing
* Spring Boot Best Practices
* Clean Code principles

---

## Status

🚧 Work in Progress
