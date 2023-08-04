# Weather API

This repository contains a Spring Boot application that provides a Weather API, which retrieves weather data from the OpenWeatherMap service for a given city and country.

## Prerequisites
- Java JDK 8 or higher
- Gradle

## Instructions to run the project

1. Clone the Repository:
   ```
   git clone https://github.com/pankajnage/weather-api.git
   cd weather-api
   ```

2. Build the Application:
   ```
   ./gradlew clean build
   ```

3. Run the Application:
   ```
   ./gradlew bootRun
   ```

   The Spring Boot application will start, and you can access the API at http://localhost:8090/weather.


## API Specification
https://petstore.swagger.io/?url=https://raw.githubusercontent.com/pankajnage/weather-api/main/Swagger.yml#/default/get_weather


## How to Use the API

The Weather API provides weather data for a specific city and country. You need to pass three query parameters in the API request:

1. `city`
2. `country`
3. `apiKey`

**API Endpoint:**

```
http://localhost:8090/weather?city={city}&country={country}&apiKey={apiKey}
```

Replace `{city}`, `{country}`, and `{apiKey}` with your desired values.

## Valid API Keys

You can use any of the following valid API keys to access the Weather API:

- `c9737ae97e4ce576407a520f30a486b1`
- `4d5ee2bc1a8c6791738b8f80a09151e7`
- `e2cecc8a1b344d02d443e86846f3fdc9`
- `2c50559d683dd7e9c3b172b0301e1ffa`
- `a778b7cc7f812536ef5960d93eccdf8a`

## Success Response

A successful API call will return the weather description for the specified city and country.

## Error Scenarios

The API handles various error scenarios with appropriate HTTP status codes:

- **404 (Not Found):** Weather data not found for the provided city and country combination.
- **503 (Service Unavailable):** Weather service is currently unavailable.