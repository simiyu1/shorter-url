# Auth Service - Spring Boot

This is an authentication service for the URL Shortener application. It provides a secure and efficient way to handle user authentication, registration, and role management. This microservice is implemented using Spring Boot, a popular Java-based framework that simplifies the development of standalone, production-grade Spring-based Applications.

## Technology Stack

- Language: Java
- Framework: Spring Boot
- Security: Spring Security and JWT
- Database: MongoDb
- Build Tool: Maven
- JDK Version: 20

## Prerequisites

1. Install Java Development Kit (JDK) 20:
    - For Windows, download the installer from the [official Oracle page](https://www.oracle.com/java/technologies/javase-jdk20-downloads.html).
    - For macOS, download the installer from the [official Oracle page](https://www.oracle.com/java/technologies/javase-jdk20-downloads.html).

2. Install [Maven](https://maven.apache.org/download.cgi):
    - For Windows, follow the installation guide [here](https://maven.apache.org/guides/getting-started/windows-prerequisites.html).
    - For macOS, follow the installation guide [here](https://maven.apache.org/install.html).

3. Install and set up MongoDB server:
    - For Windows, follow the installation guide [here](https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-windows/l).
    - For macOS, follow the installation guide [here](https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-os-x/).
    - For macOS, follow the installation guide [here](https://www.mongodb.com/docs/manual/administration/install-on-linux/).

## Installation

1. Clone the repository to your local machine:

```sh
git clone https://github.com/simiyu1/shorter-url.git
```

2. Change directory to the `auth-service` folder:

```sh
cd shorter-url/auth-service
```

3. Build the project using Maven:

```sh
mvn clean install
```

4. Start the application using Maven Spring Boot plugin:

```sh
mvn spring-boot:run
```

## Running Locally

The application should now be running locally at [http://localhost:8081](http://localhost:8081).

### Windows

1. Open the command prompt and navigate to the project directory.
2. Execute the following command to start the application:

```sh
java -jar target\auth-service-0.0.1-SNAPSHOT.jar
```

### macOS

1. Open the terminal and navigate to the project directory.
2. Execute the following command to start the application:

```sh
java -jar target/auth-service-0.0.1-SNAPSHOT.jar
```


## Code Coverage Badge

[![codecov](https://codecov.io/gh/simiyu1/shorter-url//graph/badge.svg?token=7494d7f0-1307-4b62-996b-c1b663b0a1ac)](https://codecov.io/gh/simiyu1/shorter-url/)

## API Usage

After running the microservice, you can use the following endpoints:

- **POST /api/auth/register** - Register a new user
- **POST /api/auth/login** - Authenticate an existing user and retrieve a JWT token
- **GET /api/auth/roles** - Retrieve user roles

Example usage:

1. Register a new user:

```sh
curl -X POST -H "Content-Type: application/json" -d '{"username":"john","email":"john@example.com", "password":"your_password"}' http://localhost:8081/api/auth/register
```

2. Login as a registered user:

```sh
curl -X POST -H "Content-Type: application/json" -d '{"username":"john","password":"your_password"}' http://localhost:8081/api/auth/login
```

This will return a JSON response containing the JWT token:

```json
{
  "token": "your_jwt_token",
  "type": "Bearer",
  "username": "john",
}```

