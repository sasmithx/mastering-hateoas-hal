# 🚀 Mastering HATEOAS & HAL in Spring Boot

<div align="center">

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.1-brightgreen.svg)
![Java](https://img.shields.io/badge/Java-25-orange.svg)
![Maven](https://img.shields.io/badge/Maven-4.0.0-blue.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

A comprehensive Spring Boot application demonstrating **HATEOAS** (Hypermedia as the Engine of Application State) and **HAL** (Hypertext Application Language) principles for building truly RESTful APIs.

[Features](#-features) •
[Getting Started](#-getting-started) •
[API Documentation](#-api-documentation) •
[Technologies](#%EF%B8%8F-technologies) •
[Architecture](#-architecture) •
[Contributing](#-contributing)

</div>

---

## 📋 Table of Contents

- [About](#-about)
- [Features](#-features)
- [Technologies](#%EF%B8%8F-technologies)
- [Prerequisites](#-prerequisites)
- [Getting Started](#-getting-started)
- [Architecture](#-architecture)
- [API Documentation](#-api-documentation)
- [Configuration](#%EF%B8%8F-configuration)
- [Usage Examples](#-usage-examples)
- [HAL Explorer](#-hal-explorer)
- [Project Structure](#-project-structure)
- [Best Practices](#-best-practices)
- [Contributing](#-contributing)
- [License](#-license)


---

## 📖 About

This project demonstrates the implementation of **HATEOAS** and **HAL** in Spring Boot, showcasing how to build truly RESTful APIs that follow the Richardson Maturity Model Level 3. The application provides hypermedia-driven REST endpoints that enable clients to navigate the API through links, making the API self-documenting and more discoverable.

### What is HATEOAS?

HATEOAS (Hypermedia as the Engine of Application State) is a constraint of the REST application architecture that distinguishes it from other network application architectures. With HATEOAS, a client interacts with a network application whose application servers provide information dynamically through hypermedia.

### What is HAL?

HAL (Hypertext Application Language) is a simple format that gives a consistent and easy way to hyperlink between resources in your API. It's a standard format for representing resources and their relationships using hyperlinks.

---

## ✨ Features

- ✅ **HATEOAS Implementation** - Full hypermedia-driven REST API
- ✅ **HAL Format** - Industry-standard hypermedia format
- ✅ **RESTful CRUD Operations** - Complete Product management
- ✅ **Resource Assemblers** - Clean separation of concerns
- ✅ **HAL Explorer** - Interactive API browser
- ✅ **Data Validation** - Jakarta Bean Validation
- ✅ **Exception Handling** - Global exception handler
- ✅ **PostgreSQL Integration** - Production-ready database
- ✅ **JPA/Hibernate** - Object-relational mapping
- ✅ **Lombok** - Reduced boilerplate code
- ✅ **Spring Boot DevTools** - Enhanced development experience
- ✅ **Profile-based Configuration** - Environment-specific settings

---

## 🛠️ Technologies

### Core Technologies

| Technology | Version | Description |
|------------|---------|-------------|
| **Spring Boot** | 4.0.1 | Application framework |
| **Java** | 25 | Programming language |
| **Maven** | 4.0.0 | Build tool |
| **PostgreSQL** | Latest | Database |

### Key Dependencies

- **Spring HATEOAS** - Hypermedia support
- **Spring Data JPA** - Data persistence
- **Spring Web MVC** - Web layer
- **Spring Data REST HAL Explorer** - API browser
- **Jakarta Validation** - Bean validation
- **Lombok** - Code generation
- **Spring Boot DevTools** - Development utilities

---

## 📦 Prerequisites

Before running this application, ensure you have the following installed:

- ☕ **Java 25** or higher
- 🗄️ **PostgreSQL** database server
- 📦 **Maven 3.6+** (or use the included Maven wrapper)
- 🔧 **Git** (optional, for cloning)
- 💻 **IDE** (IntelliJ IDEA, Eclipse, or VS Code recommended)

---

## 🚀 Getting Started

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/sasmithx/mastering-hateoas-hal.git
cd mastering-hateoas-hal
```

### 2️⃣ Configure Database

Create a PostgreSQL database and update the configuration:

```bash
# Create database
createdb hateoas_db
```

Update `src/main/resources/application-dev.properties`:

```properties
spring.datasource.url=jdbc:postgresql://{root}:{port}/hateoas_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3️⃣ Build the Project

Using Maven Wrapper (recommended):

```bash
# Windows
.\mvnw clean install

# Linux/Mac
./mvnw clean install
```

Or using Maven:

```bash
mvn clean install
```

### 4️⃣ Run the Application

Using Maven Wrapper:

```bash
# Windows
.\mvnw spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

Or using Maven:

```bash
mvn spring-boot:run
```

### 5️⃣ Access the Application

- **API Base URL**: `http://localhost:5555/hateoas`
- **HAL Explorer**: `http://localhost:5555/hateoas`
- **Health Check**: `http://localhost:5555/hateoas/api/v1/health`

---

## 🏗️ Architecture

This application follows a **layered architecture** pattern:

```
┌─────────────────────────────────────┐
│         Controller Layer            │
│   (REST Endpoints, Request/Response)│
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│         Assembler Layer             │
│   (HATEOAS Link Generation)         │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│         Service Layer               │
│   (Business Logic)                  │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│         Repository Layer            │
│   (Data Access)                     │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│         Database (PostgreSQL)       │
└─────────────────────────────────────┘
```

### Key Components

- **Controllers**: Handle HTTP requests and responses
- **Assemblers**: Transform entities to HATEOAS models with links
- **Services**: Contain business logic
- **Repositories**: Manage data persistence
- **DTOs**: Transfer objects for requests and responses
- **Entities**: JPA entities representing database tables

---

## 📚 API Documentation

### Base URL

```
http://localhost:5555/hateoas/api/v1
```

### Endpoints

#### Product Management

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| `POST` | `/products` | Create a new product | `ProductRequest` |
| `GET` | `/products` | Get all products | - |
| `GET` | `/products/{id}` | Get product by ID | - |
| `PUT` | `/products/{id}` | Update product | `ProductRequest` |
| `DELETE` | `/products/{id}` | Delete product | - |

#### Health Check

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/health` | Check application health |

---

## 💡 Usage Examples

### Create a Product

**Request:**

```bash
curl -X POST http://localhost:5555/hateoas/api/v1/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Laptop",
    "price": 1299.99
  }'
```

**Response (HAL+JSON):**

```json
{
  "id": 1,
  "name": "Laptop",
  "price": 1299.99,
  "_links": {
    "self": {
      "href": "http://localhost:5555/hateoas/api/v1/products/1"
    },
    "products": {
      "href": "http://localhost:5555/hateoas/api/v1/products"
    },
    "update": {
      "href": "http://localhost:5555/hateoas/api/v1/products/1"
    },
    "delete": {
      "href": "http://localhost:5555/hateoas/api/v1/products/1"
    }
  }
}
```

### Get All Products

**Request:**

```bash
curl http://localhost:5555/hateoas/api/v1/products
```

**Response (HAL+JSON):**

```json
{
  "_embedded": {
    "productResponseList": [
      {
        "id": 1,
        "name": "Laptop",
        "price": 1299.99,
        "_links": {
          "self": {
            "href": "http://localhost:5555/hateoas/api/v1/products/1"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost:5555/hateoas/api/v1/products"
    }
  }
}
```

### Get Product by ID

**Request:**

```bash
curl http://localhost:5555/hateoas/api/v1/products/1
```

### Update Product

**Request:**

```bash
curl -X PUT http://localhost:5555/hateoas/api/v1/products/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Gaming Laptop",
    "price": 1599.99
  }'
```

### Delete Product

**Request:**

```bash
curl -X DELETE http://localhost:5555/hateoas/api/v1/products/1
```

---

## 🔍 HAL Explorer

This project includes the **Spring Data REST HAL Explorer**, an interactive browser for HAL-formatted APIs.

Access it at: `http://localhost:5555/hateoas`

The HAL Explorer allows you to:
- Browse API endpoints interactively
- Follow hypermedia links
- View the API structure
- Test API calls directly from the browser

---

## 🗂️ Project Structure

```
mastering-hateoas-hal/
├── src/
│   ├── main/
│   │   ├── java/com/sasmithx/hateoas/
│   │   │   ├── HateoasApplication.java       # Main application class
│   │   │   ├── assembler/
│   │   │   │   └── ProductModelAssembler.java # HATEOAS link builder
│   │   │   ├── controller/
│   │   │   │   ├── HealthController.java     # Health check endpoint
│   │   │   │   └── ProductController.java    # Product REST API
│   │   │   ├── dto/
│   │   │   │   ├── ProductRequest.java       # Request DTO
│   │   │   │   └── ProductResponse.java      # Response DTO
│   │   │   ├── entity/
│   │   │   │   └── Product.java              # JPA entity
│   │   │   ├── exception/
│   │   │   │   └── GlobalExceptionHandler.java # Exception handling
│   │   │   ├── repository/
│   │   │   │   └── ProductRepository.java    # Data access layer
│   │   │   └── service/
│   │   │       ├── ProductService.java       # Service interface
│   │   │       └── ProductServiceImpl.java   # Service implementation
│   │   └── resources/
│   │       ├── application.properties        # Main configuration
│   │       └── application-dev.properties    # Dev environment config
│   └── test/
│       └── java/com/sasmithx/hateoas/
│           └── HateoasApplicationTests.java  # Test class
├── pom.xml                                   # Maven dependencies
├── mvnw                                      # Maven wrapper (Linux/Mac)
├── mvnw.cmd                                  # Maven wrapper (Windows)
├── LICENSE                                   # License file
└── README.md                                 # This file
```

---

## ⚙️ Configuration

### Application Properties

#### Main Configuration (`application.properties`)

```properties
spring.application.name=hateoas
server.servlet.context-path=/hateoas
spring.profiles.active=dev
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=5555
```

#### Development Profile (`application-dev.properties`)

Create this file and add your database configuration:

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/hateoas_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Configuration
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true

# Logging
logging.level.org.springframework.web=DEBUG
logging.level.com.sasmithx.hateoas=DEBUG
```

### Environment Variables

You can also use environment variables:

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/hateoas_db
export SPRING_DATASOURCE_USERNAME=your_username
export SPRING_DATASOURCE_PASSWORD=your_password
```

---

## 🌟 Best Practices

### 1. **HATEOAS Compliance**
- Every resource includes hypermedia links
- Clients can discover API capabilities through links
- Follows Richardson Maturity Model Level 3

### 2. **Clean Architecture**
- Clear separation of concerns
- Layered architecture
- Single Responsibility Principle

### 3. **DTOs for Data Transfer**
- Separate request and response objects
- Prevents over-posting vulnerabilities
- Clean API contracts

### 4. **Input Validation**
- Bean Validation with Jakarta Validation
- Automatic validation in controllers
- Meaningful error messages

### 5. **Exception Handling**
- Global exception handler
- Consistent error responses
- Proper HTTP status codes

### 6. **Resource Assemblers**
- Centralized link creation
- Reusable HATEOAS logic
- DRY principle

### 7. **Configuration Management**
- Profile-based configuration
- Environment-specific settings
- Externalized configuration

---

## 🧪 Testing

Run tests using Maven:

```bash
# Windows
.\mvnw test

# Linux/Mac
./mvnw test
```

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. **Commit your changes**
   ```bash
   git commit -m 'Add some amazing feature'
   ```
4. **Push to the branch**
   ```bash
   git push origin feature/amazing-feature
   ```
5. **Open a Pull Request**

### Code Style

- Follow Java naming conventions
- Use meaningful variable and method names
- Add comments for complex logic
- Write unit tests for new features

---

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 📊 Project Status

![Status](https://img.shields.io/badge/Status-Active-success.svg)
![Maintenance](https://img.shields.io/badge/Maintained-Yes-green.svg)

---

<div align="center">

### ⭐ Star this repository if you find it helpful!

Made with ❤️ by sasmithx

</div>

