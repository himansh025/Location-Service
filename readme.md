# Uber Location Service

A Java-based microservice for managing driver locations in an Uber-like system. Built with **Spring Boot**, **Redis**, and **Spring Data Redis**, this service allows saving driver locations and fetching nearby drivers efficiently.

---

## Microservices

This Uber project is split into multiple microservices:

| Service          | Description                                                                 | Link                                                                      |
|------------------|-----------------------------------------------------------------------------|---------------------------------------------------------------------------|
| Entity Service   | Manages core entities like Booking, Passenger, Driver, Review, and Locations | [GitHub Repo](https://github.com/himansh025/Comman-Entity)                |
| Booking Service  | Handles ride bookings, availability, and scheduling                         | [GitHub Repo](https://github.com/himansh025/Booking-Service)              |
| Socket Service   | Handles asynchronous requests between booking, driver, and passenger        | [GitHub Repo](https://github.com/himansh025/Uber-SocketServer.git)        |
| Auth Service     | Handles authentication & authorization for endpoints                        | [GitHub Repo](https://github.com/himansh025/AuthService)                  |
| Location Service | Manages driver locations and finds nearby drivers using Redis               | [GitHub Repo](https://github.com/himansh025/Location-Service)             |
| Review Service   | Manages ride reviews for passengers and drivers                             | [GitHub Repo](https://github.com/himansh025/ReviewServices)               |

---

## Table of Contents

- [About](#about)
- [Features](#features)
- [API Endpoints](#api-endpoints)
- [Dependencies](#dependencies)
- [Setup](#setup)
- [License](#license)

---

## About

The **Location Service** is responsible for:

- Saving driver locations in Redis.
- Fetching nearby drivers within a defined radius.
- Using **Redis Geo operations** for efficient geospatial queries.

This service integrates with other microservices like Booking and Entity Service for a full Uber-like system.

---

## Features

- Save/update driver location.
- Find nearby drivers within a configurable radius.
- High-performance geospatial queries using Redis.
- Spring Boot + Redis integration with **Spring Data Redis**.

---

## API Endpoints

| Endpoint                  | Method | Description                                |
|----------------------------|--------|--------------------------------------------|
| `/api/location`            | POST   | Save or update driver location             |
| `/api/location/nearby/driver` | GET    | Fetch list of nearby drivers based on coordinates |

**Example Request Body:**

**Save Driver Location**
```json
{
  "driverId": "driver123",
  "latitude": 28.7041,
  "longitude": 77.1025
}

Nearby Driver Request

{
  "latitude": 28.7041,
  "longitude": 77.1025
}

Dependencies
implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'
implementation 'org.springframework.boot:spring-boot-starter-web'
implementation 'org.springframework.boot:spring-boot-starter-data-redis'
implementation 'redis.clients:jedis:6.1.0'
implementation 'com.example:Uber-Entity:0.0.2-SNAPSHOT'

compileOnly 'org.projectlombok:lombok'
developmentOnly 'org.springframework.boot:spring-boot-devtools'
annotationProcessor 'org.projectlombok:lombok'
annotationProcessor 'org.springframework.boot:spring-boot-configuration-processor'

testImplementation 'org.springframework.boot:spring-boot-starter-test'
testImplementation 'io.projectreactor:reactor-test'
testRuntimeOnly 'org.junit.platform:junit-platform-launcher'

Setup

Clone the repository:

git clone <repository-url>


Configure Redis connection in RedisConfig.java (host/port).

Run the service using Spring Boot:

./gradlew bootRun


Driver locations will be stored in Redis and can be queried for nearby drivers.


