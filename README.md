# Water Purifier Microservices Platform

A production-oriented starter structure with independently deployable business services and supporting infrastructure.

## Services

- customer-service (8081): customers, addresses, installations, warranty and AMC
- support-service (8082): complaints, assignments, visits, SLA and resolution
- inventory-service (8083): items, stock ledger, reservations and consumption
- notification-service (8084): email/SMS jobs, templates, retries and delivery history
- reporting-service (8085): event-driven projections, dashboards and exports
- api-gateway (8080): routing and OAuth2 resource-server boundary
- frontend (5173): React application shell

## Supporting services

- PostgreSQL with a separate database and credential per service
- Kafka in KRaft mode
- Keycloak for OIDC/OAuth2
- Redis
- SeaweedFS S3-compatible object storage
- Prometheus and Grafana
- Mailpit development SMTP server

## Start infrastructure and services

~~~bash
docker compose up --build
~~~

Open:

- Frontend: http://localhost:5173
- Gateway: http://localhost:8080
- Keycloak: http://localhost:8180 (admin/admin)
- S3-compatible storage (SeaweedFS): http://localhost:8333 (admin/change-this-local-secret)
- Mailpit: http://localhost:8025
- Prometheus: http://localhost:9090
- Grafana: http://localhost:3000 (admin/admin)

## Important

This is an application structure and runnable health-check foundation, not the completed business implementation. Each service owns its database. Never share tables or JPA entities between services. Add business behavior one vertical slice at a time, beginning with complaint registration in support-service.

Keycloak realm import creates the water-purifier realm, SPA client and roles. Production secrets must not use the local defaults.
# water-purifier-platform
