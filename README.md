# Ticket Booking System - Backend

This repository contains the backend for a robust, scalable, and secure ticket booking platform. Built with Java, Spring Boot, and PostgreSQL, it leverages modern security standards like OAuth 2.0 and JWT to deliver a production-ready solution.

## Table of Contents

- [Core Features](#core-features)
- [Technology Stack](#technology-stack)
- [Architecture & Design](#architecture--design)
  - [Layered Architecture](#layered-architecture)
  - [API Design](#api-design)
  - [Database Schema](#database-schema)
- [Security Implementation](#security-implementation)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Configuration](#configuration)
  - [Running the Application](#running-the-application)

## Core Features

- **OAuth 2.0 Authentication:** Secure user authentication using the Authorization Code Flow with an external provider (e.g., Auth0, Keycloak).
- **JWT-Based Authorization:** Stateless API security using JSON Web Tokens (JWTs) for authenticated requests.
- **Role-Based Access Control (RBAC):** Granular control over API endpoints based on user roles (e.g., `USER`, `ADMIN`).
- **Token Refresh Mechanism:** Seamlessly renews access tokens using refresh tokens for an uninterrupted user experience.
- **Event & Ticket Management:** Core business logic for creating, viewing, and managing events and tickets.
- **Advanced Data Modeling:** Includes support for relationships (One-to-One, Many-to-Many), composite keys, and performance-optimized indexing.
- **Centralized Exception Handling:** Provides consistent and predictable error responses across the API.

## Technology Stack

- **Framework:** Spring Boot 3
- **Language:** Java 17
- **Security:** Spring Security, OAuth 2.0, JWT
- **Database:** PostgreSQL
- **Data Access:** Spring Data JPA, Hibernate
- **Build Tool:** Apache Maven
- **Utilities:** Lombok

## Architecture & Design

The project follows a clean, layered architecture that promotes separation of concerns, maintainability, and scalability.

### Layered Architecture

```
src/main/java/com/example/mvc_spring_postgres/
├── config/                # Security (Spring Security, CORS) and application configuration.
├── controller/api/v1/     # REST API controllers, versioned for future compatibility.
├── dto/                   # Data Transfer Objects for API request/response, decoupling API from the data model.
├── entity/                # JPA entities defining the database tables and relationships.
├── repository/            # Spring Data JPA repositories for database interaction.
├── service/               # Business logic layer, containing service interfaces and implementations.
```

- **Controller Layer:** Handles all incoming HTTP requests, validates input, and delegates to the service layer.
- **Service Layer:** Contains the core business logic. It uses DTOs to interact with the controller, ensuring the internal domain models are not exposed.
- **Repository Layer:** Abstracted data access using Spring Data JPA, providing a clean interface for database operations.

### API Design

- **RESTful & Versioned:** The API is designed following REST principles and is versioned (`/api/v1/`) to ensure backward compatibility as the application evolves.
- **Stateless:** Uses JWTs for session management, making the API stateless and easily scalable horizontally.

### Database Schema

- **JPA & Hibernate:** Leverages JPA for object-relational mapping, with entities clearly defining the database structure.
- **UUIDs for Primary Keys:** Uses `UUID` as the primary key for major entities, a best practice for distributed systems that prevents ID conflicts.
- **Indexing:** Key database columns are indexed (`@Index`) to ensure fast query performance, especially on search operations.
- **Complex Relationships:** Implements `One-to-One`, `One-to-Many`, and `Many-to-Many` relationships with composite keys (`@EmbeddedId`) to model complex domain requirements accurately.

## Security Implementation

Security is a first-class citizen in this project, built upon the robust Spring Security framework.

- **OAuth 2.0 & JWT:** The application acts as an OAuth 2.0 Resource Server, validating JWTs issued by an external Authorization Server (e.g., Auth0).
- **Centralized Security Configuration:** All security rules, including endpoint permissions, CORS policy, and token validation logic, are defined in `SecurityConfig.java`.
- **Token Validation:** JWTs are rigorously validated for issuer, audience, and expiration, with a configurable clock skew to handle minor time differences.
- **Custom JWT Claims for Roles:** The system is configured to extract user roles from a custom claim within the JWT, enabling fine-grained, role-based access control.
- **Secure Configuration:** Sensitive information like database credentials and JWT secrets are externalized into `application.yaml` files, which should never be committed to version control.

## Getting Started

### Prerequisites

- Java 17+
- Apache Maven
- PostgreSQL Database
- An OAuth 2.0 compatible Authorization Server (e.g., Auth0)

### Configuration

1.  Clone the repository.
2.  Create a copy of `application-example.yaml` and name it `application-dev.yaml`.
3.  Update `application-dev.yaml` with your PostgreSQL database credentials.
4.  Configure the `issuer-uri` in `application-dev.yaml` to point to your OAuth 2.0 provider's issuer URL.

### Running the Application

Run the application using the Spring Boot Maven plugin:

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:{application-dev.server.port}`.
