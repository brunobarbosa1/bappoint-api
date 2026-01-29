# bappoint-api

Backend API for a service scheduling SaaS system.  
**Status: Active development (pre-production).**

---

## Tech Stack

```
Backend: Spring Boot 3.x + Java 21
Database: PostgreSQL (NEON Database)
ORM: Spring Data JPA + Hibernate
Containerization: Docker
Validation: Bean Validation (Jakarta)
API Documentation: OpenAPI / Swagger
```

---

## Architecture

The project is organized by **domain**, not by technical layer:


**Inside each domain package:**
- Controller (HTTP layer)
- Service (business logic)
- Repository (data access)
- Model/Entity (JPA)
- DTO (API contracts)
- Mapper (DTO ↔ Entity)

---

### Applied patterns
- DTOs to avoid entity exposure
- Manual mappers (no MapStruct yet)
- Bidirectional OneToMany relationships with orphanRemoval
- Service layer responsible for business rules and utilities

---

## Current Features

- [x] Companies (CRUD)
- [x] Company settings
- [x] Services per company/settings
- [x] Operating hours (weekly schedule)
- [x] Off days (holidays / maintenance)
- [ ] Authentication (JWT)
- [ ] Appointments
- [ ] Users / Employees
- [ ] Notifications

---

## Docker

Run application and database locally:

```bash
docker compose up -d
```

---

## Main Endpoints

```
GET    /company/list
POST   /company/create
GET    /company/{companyId}/settings
PUT    /company/{companyId}/settings/operating_hours
POST   /company/{companyId}/settings/off-days
```

---

## Project Status

**Pre-production / Work in progress**

- Database modeling still evolving
- Frequent refactoring as domain matures
- Unit tests not implemented yet
- Authentication not implemented yet

This project is part of a real SaaS journey, built step by step with a focus on learning, architecture, and long-term maintainability.

---

**Project not in production ready yet.**
