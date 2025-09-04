# travel-journal-api

**Tools used:**
- Springboot API initializer from: https://start.spring.io/
  - Dependencies used:
    - Spring Web (needed for REST API)
    - Spring Data JPA (for working with SQL databases)
    - H2 Database (in memory database)
    - Spring Boot dev tools (for live reload and faster restarts)
    - Lombok (reduces boilerplate code)
- Postman for testing

Running for the first time: 
1. install maven for Java with: `brew install maven`
2. Type `mvn clean install` in terminal

Run with: `./mvnw spring-boot:run`
Base URL: `http://localhost:8080/api/v1/journals`

**Endpoints:**
I wanted to make sure the endpoint names were intuitive, and didn't make it too cluttered. I added the `/api` to make it clear it was the API url, and not a web URL in case it was integrated later, for organization purposes. I also added the `/v1` so that later a `v2` could be released without breaking a past version. 

- `GET /`: display all inputted journals
- `POST /`: Add new journal
- `GET /{id}:` Get specific journal by id
- `DELETE /{id}`: Delete by ID
- `PUT /{id}`: Edit journal by ID
- `GET /filter`: Filter/sort entries
  - filter by:
    - rating
    - destination
    - date
  - sample endpoints (enter request after ?. e.g.: /filter?rating=3)
    - `GET http://localhost:8080/api/v1/journals/filter?rating=5` Get all entries with rating 5
    - `GET http://localhost:8080/api/v1/journals/filter?date=2025-09-01` Get all entries from September 1
    - `GET http://localhost:8080/api/v1/journals/filter?rating=3&destination=Kolkata,%20India` Get all entries with a rating of 3 in Kolkata, India
    - `GET http://localhost:8080/api/v1/journals/filter?rating=3&page=1&size=5&sort=title,asc` Get all entries with rating of 3, sorted ascending by title

**Challenges:**
- I haven't really done error handling before, so figuring out a good way to do that was a challenge. I did some research about the GlobalExceptionHandler class, and implementing it to implement the different errors the API could encounter, and gracefully show the error, rather than the huge default error.
- The filtering function was also a bit of a challenge for me. I wanted to include filtering options for multiple different fields (rating, destination, etc.) as well as a sort option. Again, a bunch of if statements was not an efficient way to do this. I found how to use the sort, page, and size functions in Spring Boot. I also used the Specification method to chain together conditions more efficiently.


**If I had more time:**
- Support for adding images and other files (receipts, tickets, itineraries, etc): would be so fun, and make file storage more organized
- Add security and support for multiple users (each user can only see their journals)
- Add tags to entries to further filter (beach, family, etc.)
- Add a Swagger UI for easier testing (I haven't actually done this before, but I don't think it would be that difficult.)
- More aggregated endpoints (most visited destination, top rated destination, word cloud, etc.)
- Add a front end!
