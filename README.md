# Banking Management System

A RESTful Banking Management System developed using Java, Spring Boot, Spring Data JPA, and MySQL.

This project demonstrates backend development concepts such as REST API design, database integration, transaction management, input validation, and exception handling.

## Technologies Used

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Postman

## Features

- Create bank accounts
- Retrieve all accounts
- Retrieve account details by ID
- Deposit money
- Withdraw money
- Transfer money between accounts
- View account transaction history
- Validate transaction amounts
- Handle insufficient balance errors
- Handle invalid account IDs

## REST API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/accounts` | Create a bank account |
| GET | `/api/accounts` | Retrieve all accounts |
| GET | `/api/accounts/{id}` | Retrieve an account |
| POST | `/api/accounts/{id}/deposit` | Deposit money |
| POST | `/api/accounts/{id}/withdraw` | Withdraw money |
| POST | `/api/accounts/transfer` | Transfer money |
| GET | `/api/accounts/{id}/transactions` | View transaction history |

## Database Configuration

Create a MySQL database:

```sql
CREATE DATABASE banking_management;
```

Configure the application using `src/main/resources/application.properties`:

```properties
spring.application.name=banking-management
spring.datasource.url=jdbc:mysql://localhost:3306/banking_management
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
server.port=8082
```

Set the `DB_PASSWORD` environment variable to your local MySQL password before starting the application.

## How to Run

1. Clone the repository.
2. Open the project in Eclipse, IntelliJ IDEA, or another Java IDE.
3. Install Java 21 and MySQL.
4. Create the `banking_management` database.
5. Configure the `DB_PASSWORD` environment variable.
6. Run `BankingManagementApplication.java`.
7. Test the APIs using Postman.

Base URL:

```text
http://localhost:8082/api/accounts
```

## API Testing

The REST APIs were manually tested using Postman.

Tested operations include:

- Account creation
- Deposit and withdrawal
- Account-to-account transfers
- Transaction history retrieval
- Insufficient balance handling
- Invalid transaction amount handling
- Account-not-found handling

## Project Structure

```text
src/main/java/com/banking/banking_management/
|
|-- controller/
|   |-- BankAccountController.java
|
|-- entity/
|   |-- BankAccount.java
|   |-- BankTransaction.java
|
|-- repository/
|   |-- BankAccountRepository.java
|   |-- BankTransactionRepository.java
|
|-- service/
|   |-- BankAccountService.java
|
|-- exception/
|   |-- GlobalExceptionHandler.java
|
|-- BankingManagementApplication.java
```

## Important Note

This project is developed for educational and portfolio purposes.

It demonstrates banking operations through REST APIs but does not include production-grade authentication, authorization, or concurrency protection. It should not be used for real financial transactions.

## Author

**Nagalli Siddeswara**

GitHub: [NagalliSiddeswara](https://github.com/NagalliSiddeswara)
