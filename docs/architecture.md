# Architecture decisions

## Service ownership

Each business service owns its PostgreSQL database. Reporting builds projections from events. Notification reacts to domain events and never participates in an operational transaction.

## Reliability

Every service starts with an outbox table. Business records and events are committed in one local transaction. Publishers send pending events to Kafka. Consumers must record event IDs and process idempotently.

## Security

Keycloak issues tokens. The gateway and every service validate JWTs. Authorization must also be enforced inside each service. Public health endpoints reveal no business data.

## Suggested next slice

Implement POST /api/support/complaints with contact snapshot, optional customerId and installationId, complaint status history, outbox publication, validation, OpenAPI and Testcontainers.
