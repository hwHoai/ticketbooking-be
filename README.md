# Ticket Booking System - Backend API

This is the backend server for the **Ticket Booking** project, a full-stack event ticketing platform. This project was developed not only as a functional application but also as an exercise in building scalable, high-performance, and secure backend systems.

The API is designed to be robust and efficient, providing a powerful interface for the client-side application to consume.

## ✨ Key Technical Features

- **Flexible Authentication & Authorization System:**
- Integrated **Auth0** for seamless user registration and login, including social providers.
- Secured API endpoints using **Spring Security** and **JSON Web Tokens (JWT)**.
- Implemented **Role-Based Access Control (RBAC)** to manage user permissions (e.g., USER, ORGANIZER, ADMIN).
- **Advanced Event Management:**
- Provides RESTful APIs for organizers to perform full CRUD operations on events and tickets.
- Features an endpoint to store and retrieve custom, interactive event map data drawn on the client-side.
- **Performance-Oriented Architecture:**
- Designed with RESTful API principles for clean, predictable interactions.
- Optimized database queries to minimize response times.
- (Future Goal) Built with the intention to incorporate Caching (Redis) and a Message Queue (Kafka/RabbitMQ) to handle high-traffic scenarios like flash ticket sales.

## 🚀 Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot 3.x
- **Security:** Spring Security, Auth0 Integration
- **Database:** PostgreSQL
- **API:** RESTful, JSON
- **Build Tool:** Maven

## 📂 Project Structure

The project follows a standard layered architecture pattern to separate concerns and improve maintainability.

```
c:\workspace\ticketbooking-be
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── my_project
    │   │           └── ticket_booking
    │   │               ├── config         // Spring Security, Beans, etc.
    │   │               ├── controller     // REST API endpoints
    │   │               ├── dto            // Data Transfer Objects
    │   │               ├── entity         // JPA database entities
    │   │               ├── repository     // Spring Data JPA repositories
    │   │               └── service        // Business logic layer
    │   └── resources
    │       ├── application.yaml           // Main application configuration
    │       └── application-dev.yaml       // Development profile (gitignored)
    └── test
        └── java                           // Unit and integration tests
```

## 🔧 Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

- JDK 17 or later
- Maven
- A running PostgreSQL instance

### Installation

1.  **Clone the repository**
    ```bash
    git clone https://github.com/hwHoai/ticketbooking-be.git
    cd ticketbooking-be
    ```
2.  **Database Configuration**

    - Open your PostgreSQL client and create a new database.

    ```sql
    CREATE DATABASE ticket_booking_db;
    ```

3.  **Environment Configuration**

    - This project uses profile-specific configuration to separate development settings and secrets from the main codebase.
    - Navigate to `src/main/resources/` and create a new file named `application-dev.yaml`. This file is intentionally ignored by Git to keep your credentials private.
    - Add the following complete configuration to your `application-dev.yaml` file. See the guide below the code block for instructions on how to find each value.

    ```yaml
    # c:\workspace\ticketbooking-be\src\main\resources\application-dev.yaml
    server:
      port: 8080 # The port the application will run on

    spring:
      # --- DATABASE CONFIGURATION ---
      datasource:
        url: jdbc:postgresql://localhost:5432/ticket_booking_db
        username: your_postgres_username
        password: your_postgres_password
        driver-class-name: org.postgresql.Driver

      # --- JPA & HIBERNATE CONFIGURATION ---
      jpa:
        database: postgresql
        show-sql: true
        hibernate:
          ddl-auto: update # Creates/updates the DB schema. Use "validate" in production.
        properties:
          hibernate:
            format_sql: true
            use_sql_comments: true

      # --- SECURITY CONFIGURATION ---
      security:
        oauth2:
          client:
            registration:
              auth0:
                client-id: <YOUR_AUTH0_CLIENT_ID>
                client-secret: <YOUR_AUTH0_CLIENT_SECRET>
                authorization-grant-type: authorization_code
                redirect-uri: "{baseUrl}/login/oauth2/code/{registrationId}"
            provider:
              auth0:
                issuer-uri: https://<YOUR_AUTH0_APP_DOMAIN>
          resourceserver:
            jwt:
              issuer-uri: https://<YOUR_AUTH0_APP_DOMAIN>
              audiences: <YOUR_AUTH0_API_AUDIENCE>
    ```

    #### **Guide to Auth0 Configuration Values**

    You will need to retrieve values from two different places in your Auth0 dashboard: the **API** settings and the **Application** settings.

    ### Auth0 Action Triggers

    To enable Role-Based Access Control (RBAC), this project relies on custom **Auth0 Actions**. These are JavaScript functions that execute at specific points in the authentication pipeline. You must create and deploy these actions in your Auth0 dashboard under **Actions -> Library** and then add them to the **Login Flow**.

    The following actions are used in this project:

      <details>
        <summary><strong>1. `set role` (Trigger: Post User Registration)</strong></summary>

    - **Purpose:** Automatically assigns a default `USER` role to a new user immediately after they complete their registration. This ensures every new user has a baseline set of permissions from the start.

    ```javascript
    /**
     * Handler that will be called during the execution of a PostUserRegistration flow.
     *
     * @param {Event} event - Details about the user and the context in which they are logging in.
     * @param {PostUserRegistrationAPI} api - Interface whose methods can be used to change the behavior of the login flow.
     */
    exports.onExecutePostUserRegistration = async (event, api) => {
      const { CLIENT_ID, CLIENT_SECRET, DOMAIN, USER_ROLE_ID } = event.secrets;
      const tokenRes = await fetch(`https://${DOMAIN}/oauth/token`, {
        method: "POST",
        headers: { "content-type": "application/json" },
        body: JSON.stringify({
          client_id: CLIENT_ID,
          client_secret: CLIENT_SECRET,
          audience: "YOUR_AUDIENCE",
          grant_type: "client_credentials",
        }),
      });

      // @ts-ignore
      const tokenData = await tokenRes.json();
      if (!tokenData.access_token) {
        return;
      }

      const token = tokenData.access_token;
      const userId = event.user.user_id;

      await fetch(`AUTH0_ASSIGN_ROLE_API`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({
          users: [userId],
        }),
      });
    };
    ```

      </details>

      <details>
        <summary><strong>2. `assign role claim` (Trigger: Login / Post Login)</strong></summary>

    - **Purpose:** After a user successfully logs in, this action fetches their assigned roles and adds them into a custom claim within the Access Token. Spring Security is configured to read this claim to enforce endpoint security.

    ```javascript
    /**
     * Handler that will be called during the execution of a PostLogin flow.
     *
     * @param {Event} event - Details about the user and the context in which they are logging in.
     * @param {PostLoginAPI} api - Interface whose methods can be used to change the behavior of the login flow.
     */
    exports.onExecutePostLogin = async (event, api) => {
      if (event.authorization) {
        api.accessToken.setCustomClaim(
          "https://ticketbooking.com/roles",
          event.authorization.roles
        );
      }
    };
    ```

      </details>

      <details>
        <summary><strong>3. `social login set role` (Trigger: Login / Post Login)</strong></summary>

    - **Purpose:** This action handles role assignment specifically for users signing in via social providers (e.g., Google, GitHub). It ensures that on their first social login, they are assigned the default `USER` role if they don't already have one.

    ```javascript
    /**
     * Handler that will be called during the execution of a PostLogin flow.
     *
     * @param {Event} event - Details about the user and the context in which they are logging in.
     * @param {PostLoginAPI} api - Interface whose methods can be used to change the behavior of the login flow.
     */
    exports.onExecutePostLogin = async (event, api) => {
      if (
        event.stats.logins_count > 1 &&
        event.authorization?.roles &&
        event.authorization?.roles.length > 0
      ) {
        return;
      }

      const { CLIENT_ID, CLIENT_SECRET, DOMAIN, USER_ROLE_ID } = event.secrets;
      const tokenRes = await fetch(`https://${DOMAIN}/oauth/token`, {
        method: "POST",
        headers: { "content-type": "application/json" },
        body: JSON.stringify({
          client_id: CLIENT_ID,
          client_secret: CLIENT_SECRET,
          audience: "YOUR_AUDIENCE",
          grant_type: "client_credentials",
        }),
      });

      // @ts-ignore
      const tokenData = await tokenRes.json();
      if (!tokenData.access_token) {
        return;
      }

      const token = tokenData.access_token;
      const userId = event.user.user_id;

      await fetch(`AUTH0_ASSIGN_ROLE_API`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({
          users: [userId],
        }),
      });
    };
    ```

      </details>

4.  **Run the Application with the 'dev' Profile**
    - To tell Spring Boot to use your `application-dev.yaml` configuration, you need to activate the `dev` profile.
    - **From your IDE:** Set the active Spring profile to `dev` in your application's run/debug configuration.
    - **From the command line:** Use the following Maven command:
      ```bash
      # The 'dev' profile will load application-dev.yaml
      mvn spring-boot:run -Dspring-boot.run.profiles=dev
      ```
    - The server will start on `http://localhost:8080`.

## 📚 Core API Endpoints

The following table lists the API endpoints implemented in the project's controllers.

| Endpoint                           | Method | Description                                         | Auth Required  |
| :--------------------------------- | :----- | :-------------------------------------------------- | :------------- |
| `/api/v1/user/info`                | `GET`  | Retrieves the logged-in user's profile information. | Yes            |
| `/api/v1/auth/access_token/{code}` | `GET`  | Get access token                                    | No (uses code) |
| `/api/v1/auth/refresh_token`       | `POST` | Refresh access token                                | No (uses RT)   |

> **Note**: "Auth Required: Yes" implies that a valid **Access Token (AT)** must be included in the request header. "Use RT" means a valid **Refresh Token (RT)** is needed for that specific operation. Code is the auth code given on the callback url when login.
