# Weather Information Service

This is a simple Spring Boot application that provides the current weather information for selected cities in New
Zealand.
It puts the most recent 3 city lookups in-memory and uses a dummy secondary API if the city is not in-memory.

## Features

* Fetch current weather for supported cities
* In-memory dataset for up to 3 recent city weather records
* Dummy external call for fallback (secondary API)
* Error handling for unknown cities or missing input
* Integrated Swagger UI for API documentation
* Integration testing using @SpringBootTest
* error and info level logging

## How It Works

1. When a request is made to `/api/weather/{city}`, the application first checks if the city exists in the in-memory
   dataset.
2. If not, it attempts to retrieve the data from the dummy external source `SecondaryWeatherDataApi`.
3. If still not found, a `404 City Not Found` is returned.
4. If the input is empty (like `/api/weather`), a `400 Bad Request` is returned.
5. The in-memory dataset holds only 3 cities. When a fourth city is requested, any one of the earlier ones is removed.

## API Endpoints

### GET /api/weather/{city}

Fetches weather information for the specified city.

* Example: `GET http://localhost:7860/api/weather/auckland`
* Returns JSON:
  {
  "city": "Auckland",
  "temperature": "24",
  "unit": "C",
  "date": "06/08/2025",
  "condition": "cloudy"
  }

```

### GET /api/weather

Returns 400 error for missing city input.

* Example response:

```json
{
  "error": "City name must be provided in the URL path"
}
```

## Swagger UI

To explore and test the API using Swagger when the application is up and running on port 7860:
* Use: http://localhost:7860/swagger-ui.html

* For API docs : http://localhost:7860/v3/api-docs

## Technologies Used
* Spring Boot 3.2.5
* Java 17
* Swagger / OpenAPI (springdoc-openapi)
* SLF4J Logging

## How to Run
1. Clone the git repository from https://github.com/ahamadfaiz7/weatherInformation
2. Development was carried out on the feature/weatherInfo branch, followed by a merge request to the main branch.
   You can clone either the feature/weatherInfo or main branch to access the complete codebase.
2. Make sure Java 17 and Maven are installed.
3. Run the application using:
```bash
mvn clean
mvn install ## It will also run the integration tests.
mvn spring-boot:run
mvn test ## to run only the integration tests.
```
4. Open browser at: `http://localhost:7860/swagger-ui.html` to access Swagger UI to test the API.
5. Test from browser/postman when the application is running-> http://localhost:7860/api/weather/hamilton

##Integration Testing
------------------------------
Integration tests are implemented using the @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
annotation.
This allows the application context to be loaded and tests to run against an actual running server with any random port.
It helps verify the behavior of the weather API endpoints, including response correctness, HTTP status, and fallback
logic.

##Logging
-----------------------------------
The application uses SLF4J with LoggerFactory for logging.
INFO level logging is enabled to capture application behavior such as:
Fetching weather data
in-memory key/value removed
External API lookup
Exception triggers and error messages
You can find these logs in the console output during  the application runs.

-------------------------------------------------------------------------------------------------------------------------