### What Can I Learn From This Project? 
Working on this project will give you practical experience and knowledge in the following core areas:

`Spring Boot Fundamentals:` Learn how to quickly and easily create a web application using the Spring framework.

`RESTful API Development:` Understand how to build API endpoints that manage resources using HTTP methods (GET, POST, PUT, DELETE).

`Centralized Exception Handling:` Learn how to manage application-wide errors (e.g., data not found or permission errors) from a single location, providing users with meaningful and consistent error messages.

`Layered Architecture:` Grasp how to structure a project into distinct layers like Controller, Service, and Repository, and understand the responsibilities of each.

`Dependency Injection:` Master one of Spring's most powerful features, Dependency Injection (DI), to manage dependencies between your classes.

### Key Technologies and Structures
The following essential technologies and Java structures form the foundation of this project.

`Java 17:` The core programming language for the project.

`Maven:` Used for managing project dependencies and building the application.

`Spring Boot:` A framework that simplifies the creation of stand-alone, production-grade Spring-based applications.

`Spring Web`: A module used for building web and RESTful APIs.

`Spring Data JPA:` Simplifies database operations and reduces boilerplate code.

`Spring Security:` Adds a security layer to the application, handling both Authentication and Authorization.

`H2 Database:` An in-memory database used for development and testing. It allows you to run the application without needing a separate database server.

### Important Annotations and Their Purposes
Here are some of the key annotations used in the project that demonstrate the power of Spring:

`@RestController:` Marks a class as a REST API controller, indicating that its methods will return data in formats like JSON or XML.

`@Service:` Indicates that a class contains the business logic. Spring scans for these classes to manage them as components.

`@Repository:` Designates a class responsible for interacting with the database. Spring Data JPA automatically creates repository implementations for interfaces marked with this annotation.

`@ControllerAdvice:` Used for centralized exception handling across the entire application. It allows you to catch and process exceptions from all controllers in one place.

`@ExceptionHandler:` Used within an @ControllerAdvice class to catch and handle a specific type of exception. For example, it's used to return a custom response when a DataNotFoundException occurs.

`@Autowired:` Tells Spring to automatically inject an object (a dependency) into a class.

`@RequestBody:` Binds the body of an HTTP request to a Java object.

`@PathVariable:` Maps a dynamic value from the URL (e.g., the {id} in /todos/{id}) to a method parameter.
