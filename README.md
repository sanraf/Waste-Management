# Waste Sorting Mobile Application Backend

## Overview
This project is a Spring Boot application developed for Enviro365's waste sorting mobile application. It provides a RESTful API to enable data exchange between the mobile frontend and the backend server. The application aims to promote sustainable waste management practices by offering functionalities such as waste category lookup, disposal guidelines retrieval, and recycling tips.

---

## Features
- CRUD operations for:
- Waste Categories
- Disposal Guidelines
- Recycling Tips
- JSON-formatted responses for all endpoints.
- Input validation using Spring Boot validation annotations.
- Error handling with meaningful HTTP response codes.
- Database integration with Spring Data JPA.

---

## Prerequisites
- **Java 17+**
- **Maven 3.9+**
- **Database**: H2 (in-memory), MySQL, or any other supported database.

---


1. Build the project:
   ```bash
   mvn clean install
   ```

2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

3. Access the application at:
   - **H2 Console**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console) (if H2 is used)
   - **API Endpoints**: [http://localhost:8080/api/v1](http://localhost:8080/api)

---

## API Endpoints

### Waste Category
- **GET /api/v1/category/get-all**: Retrieve all waste category.
- **GET /api/v1/category/get/{id}**: Retrieve a specific waste category by ID.
- **POST /api/v1/category/save**: Add a new waste category.
- **PUT /api/v1/category/update/{id}**: Update an existing waste category.
- **DELETE /api/v1/category/delete/{id}**: Delete a waste category.

### Disposal Guidelines
- **GET /api/v1/guideline/get-all**: Retrieve all disposal guidelines.
- **GET /api/v1/guideline/get/{id}**: Retrieve a specific guideline by ID.
- **POST /api/v1/guideline/save/{categoryId}**: Add a new guideline.
- **PUT /api/v1/guideline/update/{id}**: Update an existing guideline.
- **DELETE /api/v1/guideline/delete/{id}**: Delete a guideline.

### Recycling Tips
- **GET /api/v1/tip/get-all**: Retrieve all recycling tips.
- **GET /api/v1/tip/get/{id}**: Retrieve a specific tip by ID.
- **POST /api/v1/tip/save**: Add a new tip.
- **PUT /api/v1/tip/update/{id}**: Update an existing tip.
- **DELETE /api/v1/tip/delete{id}**: Delete a tip.

---

## Example JSON Payloads

### Waste Category
**POST /api/v1/category/save**
```json
{
  "name": "Plastic",
  "description": "Materials like bottles, containers, and wrappers."
}
```

### Disposal Guideline
**POST /api/v1/guideline/save**
```json
{
  "guideline": "Rinse all plastic items before recycling."
}
```

### Recycling Tip
**POST /api/v1/tip/save**
```json
{
  "tip": "Reuse jars for food storage instead of buying new containers."
}
```

---


### Example for waste category Response **POST /api/v1/category/save**
```json
{
  "statusMessage": "success",
  "statusCode": "CREATED",
  "result": {
    "id": 1,
    "name": "Composting Organic Waste",
    "description": "How to compost organic waste properly to reduce landfill use.",
    "guidelines": []
  }
}
```

---

### Example for waste category Response **GET /api/v1/category/get-all**:
```json
{
"statusMessage": "success",
"statusCode": "ACCEPTED",
"result": [
    {
        "id": 1,
        "name": "Composting Organic Waste",
        "description": "How to compost organic waste properly to reduce landfill use.",
        "guidelines": [
            {
                "id": 1,
                "guideline": "Composting Organic Waste",
                "categoryId": 1
                }
            ]
        }
    ]
}
```

### Example for Disposal Guidelines Response **POST /api/v1/guideline/save/1**
```json
{
"statusMessage": "success",
        "result": {
        "id": null,
        "guideline": "Composting Organic Waste",
        "categoryId": 1
   }
}
````

### Example for Disposal Guidelines Response **GET /api/v1/guideline/get**
```json
{
"statusMessage": "success",
"result": [
        {
        "id": 1,
        "guideline": "Composting Organic Waste",
        "categoryId": 1
        }
    ]
}
````

### Example for Recycling Tip Response **POST /api/v1/tip/save**
```json
{
"statusMessage": "success",
    "result": {
        "id": 1,
        "tip": "Reuse jars for food storage instead of buying new containers."
        }
}
`````

### Example for Recycling Tip Response **GET /api/v1/tip/get-all**
```json
{
    "statusMessage": "success",
        "result": [
            {
                "id": 1,
                "tip": "Reuse jars for food storage instead of buying new containers."
            },
            {
                "id": 2,
                "tip": "Reuse xxxx xxx xx  xxx xx xxxxxxxx xxx xx xxx."
            }
    ]
}
````

## Error Handling
- **400 Bad Request**: Invalid input data.
- **404 Not Found**: WasteEntityNotFoundException.
---

### Example Error Response
```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Waste category not found",
  "path": "/api/categories/999"
}
```

---

## Validation
- **@NotNull**: Ensures fields are not null.

### Example Validation
```java

public record WasteCategoryDto(
        Long id,
       @NotBlank(message = "category name is required")String name,
       @NotBlank(message = "category description is required")String description, List<DisposalGuideline> guidelines) {

}
```

---

## Database Configuration
### Default H2 Configuration (application.properties):
```properties
spring.datasource.url=jdbc:h2:mem:recycle
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

---


