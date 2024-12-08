
# SchoolApp REST API

This is a Java-based RESTful API for managing a school's information system. It is built using JAX-RS technology and provides functionalities to handle various entities, such as teachers, students, departments, and their relationships.

## Features

- Manage teachers, students and departments.
- Establish associations between teachers, students and departments.
- Retrieve, create, update, and delete entities through the API.
- Exception handling for missing resources and invalid data.

## Prerequisites

- Java Development Kit (JDK) 8 or later
- Maven for dependency management
- A MySQL database instance

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/nikouliciousp/schoolapp-restapi.git
   ```

2. Navigate to the project directory:
   ```bash
   cd schoolapp-restapi
   ```

3. Configure the database connection in the `application.properties` file.

4. Build the project:
   ```bash
   mvn clean install
   ```

5. Deploy the application to your favorite Java EE server (e.g., Apache TomEE, WildFly).

## API Endpoints

The API provides the following endpoints (examples):

- `GET /
- `PUT /
- `POST /
- `DELETE /
