# Spring Boot RESTful API Project

This Spring Boot project demonstrates RESTful API development using Java, Spring Data JPA, and RESTful principles. It
includes CRUD operations for managing departments and associated endpoints for creating, retrieving, updating, and
deleting department data.

## Key Features

- **REST API Concepts**: Utilizes HTTP methods (GET, POST, PUT, DELETE) for CRUD operations.
- **Loggers**: Employs logging using the SLF4J and Logback frameworks.
- **Lombok**: Enhances code readability and reduces boilerplate code.
- **Exception Handling**: Implements global exception handling for graceful error responses.
- **H2 Database**: Initially uses an H2 in-memory database for development.
- **MySQL Database**: Shifts to a MySQL database for production use.
- **Unit Testing**: Includes unit tests using JUnit and Mockito for testing components.
- **Configuration Files**: Utilizes application.properties and application.yml for configuration.
- **Spring Boot Profiles**: Utilizes profiles in application.yml for environment-specific configurations.
- **JAR File Creation**: Builds executable JAR files for deployment.
- **Actuator**: Integrates Spring Boot Actuator for monitoring and managing the application.
- **Custom Actuator Endpoints**: Adds custom endpoints for specific application monitoring.
- **Excluding Dependencies**: Demonstrates excluding dependencies from auto-configuration where necessary.

## Components and Annotations

### Controller Layer

The controller layer is responsible for handling incoming HTTP requests and returning appropriate responses.

- `@RestController`: Marks classes as RESTful controllers.
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`: Maps HTTP methods to controller methods.

### Service Layer

The service layer contains business logic and coordinates data access from the repository layer.

- `@Service`: Marks classes as service beans.
- `@Autowired`: Injects dependencies automatically.

### Repository (DAO) Layer

The repository layer interacts with the database, providing CRUD operations and data access.

- `@Repository`: Marks classes as data access objects (DAOs).
- `@Entity`: JPA annotation for marking classes as entities.
- `@Transactional`: Specifies transactional behavior for methods.

### Loggers and Lombok

- `@Slf4j`: Lombok annotation for generating logger instances.
- `@Data`: Lombok annotation for generating getters, setters, equals, hashCode, and toString methods.
- `@Getter`, `@Setter`: Generates getter and setter methods.
- `@NoArgsConstructor`: Generates a no-argument constructor.
- `@AllArgsConstructor`: Generates an all-argument constructor.

### Exception Handling

- `@ControllerAdvice`: Global exception handling for the application.
- `@ExceptionHandler`: Handles exceptions globally within the application.

## MySQL Database Configuration

For production using MySQL database:

``` mysql-sql

# My-SQL Database connection properties


spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/springboot-rest-api
spring.datasource.username=root
spring.datasource.password=9949
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql:true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

```

### Transition from application.properties to application.yml

#### Move configurations from application.properties to application.yml:

```yaml
server:
  port: 8082

spring:
  config:
    activate:
      on-profile:
        - dev
---

spring:
  profiles:
    include: qa
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    password: 9949
    url: jdbc:mysql://localhost:3306/springboot-rest-api-qa
    username: root
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```


### Configuration Files and Profiles

- `application.yml`: Main configuration file for Spring Boot.
- `application.properties`: Alternative to application.yml for property-based configuration.
- `@Configuration`: Indicates a class as providing bean definitions.
- `@PropertySource`: Specifies property files to be included in the application context.
- `@Profile`: Defines profiles for environment-specific configurations.
- `bootstrap.yml`: Configuration file for Spring Boot's bootstrap process.

## Building and Running

1. Clone the repository.
2. Update MySQL database configurations in `application.yml`.
3. Build the project using `./mvnw clean package`.
4. Run the application using `java -jar target/<jar-file-name>.jar`.
5. Access Actuator endpoints for monitoring and management.

## Testing

Unit tests are available in the `src/test` directory. Run tests using `./mvnw test`.

## Actuator Endpoints

- `/actuator/health`: Check the health of the application.
- `/actuator/info`: View application information.
- `/actuator/custom`: Add custom endpoints for specific monitoring needs.

- ### Custom Actuator Endpoint

To create a custom actuator endpoint:

``` java
package com.mansoor.springboot.rest.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "features")
public class FeatureEndpoint {
    private final Map<String, Feature> featuresMap = new ConcurrentHashMap<>();

    public FeatureEndpoint() {
        featuresMap.put("Department", new Feature(true));
        featuresMap.put("User", new Feature(false));
        featuresMap.put("Authentication", new Feature(false));
    }
    @ReadOperation
    public Map<String, Feature> features() {
        return featuresMap;
    }

    public Feature feature(@Selector String featureName){
        return featuresMap.get(featureName);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Feature {
        private boolean isEnabled;
    }
}


```

## Custom Actuator Endpoint: FeatureEndpoint

The `FeatureEndpoint` in this Spring Boot application provides information about various features available within the
system. It exposes endpoints to query the status of specific features and their enabled/disabled state.

### Purpose

The purpose of the `FeatureEndpoint` is to allow external monitoring and management of feature toggles within the
application. Feature toggles, also known as feature flags, enable or disable certain functionalities based on runtime
conditions or configuration settings.

### Endpoint Details

- **Endpoint ID**: `features`
- **Endpoints**:
    - `/actuator/features`: Returns a map of all features and their enabled/disabled status.
    - `/actuator/features/{featureName}`: Returns the enabled/disabled status of a specific feature.

### Example Usage

#### Get All Features

Send a GET request to `/actuator/features` to retrieve information about all features and their status.

Response:

```json
{
  "Department": {
    "isEnabled": true
  },
  "User": {
    "isEnabled": false
  },
  "Authentication": {
    "isEnabled": false
  }
}
```

#### Get Specific Feature Status

Send a GET request to /actuator/features/Department to retrieve the status of the "Department" feature.

Response:

``` json

{
"isEnabled": true
}
```

### Implementation Details
The FeatureEndpoint is implemented as a Spring Boot Actuator endpoint (`@Endpoint`) and utilizes the `@ReadOperation` and`
@Selector` annotations for defining read operations and selecting specific features respectively.

### Feature Class
The FeatureEndpoint includes an inner class Feature to represent a feature with its enabled/disabled state. This class
is annotated with Lombok annotations (`@Data, @NoArgsConstructor, @AllArgsConstructor`) for generating getters, setters,
constructors, and other boilerplate code.

### Features Map
The FeatureEndpoint maintains a featuresMap using a ConcurrentHashMap to store feature names as keys and their
corresponding Feature objects. Upon initialization, default features are added to this map with their initial
enabled/disabled states.

### Use Cases
Feature Toggles: Use this endpoint to dynamically enable or disable features without redeploying the application.
Monitoring: Monitor feature usage and adoption rates by querying feature status regularly.
Configuration Management: Integrate with external configuration systems to manage feature flags based on environment or
user settings.

##### By leveraging the FeatureEndpoint, developers and administrators can gain insights into feature availability and control feature toggles efficiently within the application.
