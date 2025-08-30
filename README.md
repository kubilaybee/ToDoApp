ToDoApp
Overview
Welcome to the ToDoApp! This is a simple, yet robust, web application for managing your to-do list. The application
follows a modern API-first approach, built with a RESTful architecture that ensures a clear separation of concerns
between the backend and frontend.

The backend is developed with Spring Boot, leveraging its powerful features to create a scalable and maintainable API.
It handles all the business logic, including user management, task creation, updates, and deletion. All data is
persisted to an in-memory H2 database for a lightweight and easy-to-run setup, perfect for development and demonstration
purposes.

Key Features
RESTful API: A well-designed and documented API for managing to-do items.

Authentication & Authorization: Secure user authentication using Spring Security with HTTP Basic Authentication.

Data Validation: All incoming data is validated using Jakarta Bean Validation to ensure data integrity.

Error Handling: A centralized @ControllerAdvice handles all exceptions, providing clear and meaningful error messages to
the client.

In-Memory Database: Uses H2 database for a quick and easy setup without the need for an external database.

CRUD Operations: Full functionality to Create, Read, Update, and Delete to-do items.

Technologies Used
Spring Boot: The core framework for building the application.

Spring Security: Handles all authentication and authorization logic.

Spring Data JPA & Hibernate: Provides a powerful and simple way to interact with the database.

H2 Database: A fast, in-memory relational database.

Lombok: Reduces boilerplate code in entity classes.

Jakarta Validation: Ensures data integrity by validating incoming requests.

Maven: Manages project dependencies and the build process.

Getting Started
Prerequisites
Java 17 or higher

Maven

API Endpoints
The API is secured with HTTP Basic Authentication. Use the following credentials to access the endpoints:

Username: admin

Password: admin

HTTP Method Endpoint Description
POST /todos Creates a new to-do item.
GET /todos Retrieves all to-do items for a user.
GET /todos/{id} Retrieves a single to-do item by ID.
PUT /todos Updates an existing to-do item.
DELETE /todos/{id} Deletes a to-do item by ID.
