# Weather API

This repository contains a Spring Boot application that provides a Weather API, which retrieves weather data from the OpenWeatherMap service for a given city and country.

## Prerequisites
- Java JDK 8 or higher
- Gradle

## Instructions to Execute the Solution

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

4. API Usage:
   Send a GET request to the following endpoint to fetch weather data:
   ```
   http://localhost:8090/weather?city=Melbourne&country=AUS&apiKey=c9737ae97e4ce576407a520f30a486b1
   ```

   Replace `Melbourne`, `AUS`, and `c9737ae97e4ce576407a520f30a486b1` with your desired city, country, and API key respectively. You can use one of the valid API keys mentioned below.

   **Valid API Keys**:
    - c9737ae97e4ce576407a520f30a486b1
    - 4d5ee2bc1a8c6791738b8f80a09151e7
    - e2cecc8a1b344d02d443e86846f3fdc9
    - 2c50559d683dd7e9c3b172b0301e1ffa
    - a778b7cc7f812536ef5960d93eccdf8a

   **Success Response**: The API will respond with the weather description for the provided city and country.

   **Error Scenarios**:
    - If the API cannot find weather data for the provided city and country combination, it will respond with a `404 Not Found` status.
    - If the OpenWeatherMap service is currently unavailable, the API will respond with a `503 Service Unavailable` status.
