# HealthCare Content Recommendation API

A Spring Boot application that provides RESTful APIs for healthcare content management and recommendations. It integrates with a FastAPI service to generate content recommendations.

## Features

- Retrieve healthcare content by ID
- Get recommended content for a given item
- OpenAPI (Swagger) documentation
- Reactive endpoints using Spring WebFlux

## Tech Stack

- Java 22
- Spring Boot 3.4.x
- Spring WebFlux
- Spring Data JPA
- PostgreSQL
- Lombok
- Reactor
- OpenAPI (springdoc)
- Maven

## Getting Started

### Prerequisites

- Java 22
- Maven
- PostgreSQL
- FastAPI recommendation service running at `http://127.0.0.1:5000`

### Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/sbm4034/HealthCareContentRecommendationService.git
   cd HealthCareContentRecommendation
   
2. **Configure PostgreSQL:**
   - Create a database named `healthcare_content` with schema `org`.
   - Update the `application.properties` file with your PostgreSQL credentials.
3. **Run the FastAPI service:**
   - Clone the repository for the FastAPI service:
   ```git clone https://github.com/sbm4034/AI_Projects_Experiments```
   - Ensure you have setup python (3.9 or above) environment with FastAPI installed. 
   - Run the FastAPI service using following command:
   ```  uvicorn recommendation_api:app --reload --port 5000```
   - If the fast API isn't installed, you can install it using pip:
   ```pip install uvicorn``` or ```pip install fastapi``` 
   - Upon successful installation of fastapi, re-run the ```uvicorn recommendation_api:app --reload --port 5000```
   - Ensure the FastAPI service is running at `http://127.0.0.1:5000'
   - Invoke the endpoint for verifying the FastAPI service works as expected by invoking the curl ```curl --location 'http://127.0.0.1:5000/recommend' \
     --header 'Content-Type: application/json' \
     --data '{
     "content_id": 1,
     "num_recommendations": 3
     }'```

4. Run the following command to insert data into postgres by executing file ```python populate_db.py```
5**Build and run the Spring boot application:**
  ```sh
  mvn spotless:apply 
  mvn clean install
  ```
6. Verify the recommendation API by invoking the curl ```curl --location 'http://localhost:8080/api/v1/content/2/recommendations'```
7. You can also access the OpenAPI documentation at `http://localhost:8080/swagger-ui.html`.
   
