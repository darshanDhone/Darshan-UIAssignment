# Customer Reward Assignment

This is a Spring Boot application designed to manage customer rewards based on their transactions. It supports features such as customer registration, login, transaction management, and reward points calculation.

## Features

- **Customer Management**: Register new customers, login, and fetch customer details.
- **Transaction Management**: Add, update, delete, and fetch customer transactions.
- **Reward Points Calculation**: Earn rewards based on transaction amount and spend details.
- **Security**: In-memory authentication and secure endpoints with Spring Security.

## Technologies

- **Java 21**
- **Spring Boot 3.4.4**
- **Spring Security** (with basic in-memory authentication)
- **JPA** (Java Persistence API) for database operations
- **H2** (for in-memory database) and **MySQL** (optional) for persistence
- **JUnit 5** for unit testing

## API Endpoints

### Customer Endpoints

- **POST `/api/customers/register`**: Register a new customer.
- **POST `/api/customers/login`**: Login a customer with a username and password.
- **GET `/api/customers/getAll`**: Retrieve all customer records.

### Transaction Endpoints

- **POST `/api/transactions/add`**: Add a new transaction for a customer.
- **GET `/api/transactions/getByID/{id}`**: Retrieve a transaction by its ID.
- **PUT `/api/transactions/update/{id}`**: Update a transaction by ID.
- **DELETE `/api/transactions/{id}`**: Delete a transaction by ID.

### Reward Points Endpoints

- **GET `/api/rewards/customer/{customerId}`**: Get reward points for a specific customer.
- **GET `/api/rewards/all`**: Retrieve all available reward points.
- **GET `/api/rewards/customer/{customerId}/summary`**: Get a summary of rewards for a specific customer.

## Setup Instructions

### Prerequisites

- **Java 21**
- **Maven** (or Gradle)
- **MySQL** (optional) or **H2** for the database

### Running the Application

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/customer-reward-assignment.git

2. Navigate to the project directory:

```bash
cd customer-reward-assignment

3. Build and run the application with Maven:

```bash
mvn spring-boot:run

The application will be available at http://localhost:8080.

Database Configuration
To use MySQL, update the application.properties with your MySQL credentials:

properties

spring.datasource.url=jdbc:mysql://localhost:3306/customer_rewards
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
Note: H2 is used as the default database for local development, so you can skip this step if using H2.