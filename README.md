# Java Project with Spring Boot

## Description

This project is a Java application developed using the Spring Boot framework. It comprises two microservices that communicate with each other: one for managing clients and another for handling accounts. The application is designed to demonstrate the integration and functioning of various components and technologies in a microservices environment.

## Features

- **Client Microservice**: Manages client information, including CRUD (Create, Read, Update, Delete) operations.
- **Account Microservice**: Handles accounts associated with clients, also allowing CRUD operations.
- **Service Communication**: The two microservices communicate with each other to share and synchronize relevant data.
- **PostgreSQL Database**: PostgreSQL is used as the database management system to store and manage service information.
- **RabbitMQ**: Implements RabbitMQ for asynchronous communication and queue handling between microservices.
- **Testing with Karate and JUnit**: Includes integrated tests using Karate for API testing and JUnit for unit and integration testing.
- **Docker**: The application and its components are containerized using Docker, facilitating deployment and environment consistency.

## Technologies Used

- Java
- Spring Boot
- PostgreSQL
- RabbitMQ
- Karate
- JUnit
- Docker

## Project Structure

The project is divided into two main microservices:

1. **Client Microservice**: Responsible for managing client data.
2. **Account Microservice**: In charge of handling client accounts.

Each microservice has its database and communicates with the other through RabbitMQ.

## Configuration and Deployment

The application was dockerized using a Docker Compose file, which configures the microservices, the PostgreSQL database, and the RabbitMQ messaging queue. All components are contained in separate containers but are connected within the same network to facilitate communication between them.

## Running Tests

For testing, the project utilized JUnit, Mockito, and Karate. Additionally, in the Docker Compose setup, a Dockerfile was created to separate the testing from the integration and deployment of the applications. This setup first initiates the applications and then carries out unit testing with JUnit, Mockito, and Karate.

## Author

Gilbert Andres Lemus 

This project is an example of integrating various technologies into a Java application with Spring Boot, demonstrating microservices architecture patterns and integrated testing.
