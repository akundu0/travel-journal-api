# travel-journal-api

Tools used:
- Springboot API initializer from: https://start.spring.io/
  - Dependencies used:
    - Spring Web (needed for REST API)
    - Spring Data JPA (for working with SQL databases)
    - H2 Database (in memory database)
    - Spring Boot dev tools (for live reload and faster restarts)
    - Lombok (reduces boilerplate code)
- Postman for testing

Run with: ./mvnw spring-boot:run
Base URL: http://localhost:8080/api/v1/journals/

Endpoints:
- GET /: display all inputted journals
- POST /: Add new journal
- GET /{id}: Get specific journal by id
- DELETE /{id}: Delete by ID
- PUT /{id}: Edit journal by ID
- GET /filter: Filter by rating and/or date (enter search after ?. e.g.: /filter?rating=3)


