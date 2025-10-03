# Ticket Booking Backend

A robust, secure, and scalable backend for a ticket booking system, built with Java, Spring Boot, and PostgreSQL.

## Architecture

- **Modular Structure:**  
  Organized by feature and responsibility, following best practices for maintainability and scalability.
- **Layered Design:**  
  - **Controller Layer:** Handles API requests and responses.
  - **Service Layer:** Encapsulates business logic.
  - **Repository Layer:** Manages data persistence with Spring Data JPA.
  - **DTOs:** Clean separation between internal models and API contracts.
- **Configuration:**  
  Centralized configuration for security and environment-specific settings.

```
src/main/java/com/example/mvc_spring_postgres/
├── config/                # Application and security configuration
├── controller/api/v1/     # REST API controllers (versioned)
├── dto/                   # Data Transfer Objects (request/response)
├── entity/                # JPA entities (domain models)
├── repository/            # Data access layer
├── service/               # Business logic and exception handling
```

## Security

- **Spring Security Integration:**  
  - Centralized security configuration (`SecurityConfig`)
  - JWT-based authentication for stateless, secure API access
  - Role-based access control for endpoints
- **Environment-Specific Config:**  
  - Sensitive data (DB credentials, secrets) managed via YAML config files
  - SSL enforced for database connections

## Features

- **User Authentication & Authorization:**  
  - Secure login, JWT token issuance, and refresh
  - User info and role management
- **Exception Handling:**  
  - Global exception handler for consistent API error responses
- **Database Integration:**  
  - PostgreSQL with JPA/Hibernate for ORM
  - Auto schema update and SQL logging for development
- **API Versioning:**  
  - Future-proofed with `/api/v1/` structure

## Getting Started

1. **Clone the repository**
2. **Configure environment variables** in `src/main/resources/application-dev.yaml`
3. **Run with Maven:**
   ```
   ./mvnw spring-boot:run
   ```

---

**Designed for extensibility, security, and real-world scalability.**

