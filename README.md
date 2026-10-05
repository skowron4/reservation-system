# 🏢 Room Reservation System [🚧 WIP]

> **Note:** This project is currently a **Work-In-Progress (WIP)**. Core infrastructure and database schemas are defined, but business logic, REST APIs, and the frontend user interface are under active development.

A full-stack web application designed for managing room reservations within various buildings. It allows users to book rooms, managers to oversee building schedules, and administrators to manage the entire system.

## 🛠 Tech Stack

**Backend:**
- Java 21
- Spring Boot (Data JPA, WebMVC, Validation)
- PostgreSQL & Flyway (Database Migrations)
- MapStruct & Lombok

**Frontend:**
- Angular 17 (Standalone Components, Routing)
- TypeScript & RxJS
- SCSS

**Infrastructure:**
- Docker & Docker Compose
- GitHub Actions (CI)

---

## 🚀 Getting Started (Local Development)

### Prerequisites
- Docker and Docker Compose
- Java 21 & Maven (for local backend development)
- Node.js 18+ & npm (for local frontend development)

### 1. Environment Setup
Clone the repository and set up your environment variables:
```bash
cp .env.example .env
```
### 2. Running with Docker (Fullstack)
To spin up the database, backend, and frontend in containers:
```bash
docker compose up --build
```
- Frontend will be available at: http://localhost
- Backend API will be available at: http://localhost:8080

### 3. Running Locally (Dev Mode)
Database:
You can run just the PostgreSQL database using the override file (exposes port 5432):
```bash
cp docker-compose.override.example.yaml docker-compose.override.yaml
docker compose up postgres-db
```

Backend:
```bash
cd reservation-system-backend
./mvnw spring-boot:run
```
Frontend:
```bash
cd reservation-system-frontend
npm install
npm start
```

## 🗺️ Roadmap & Current State

### ✅ Implemented
- [x] **Database Schema:** Flyway migrations (`V1__init_schema.sql`) including advanced PostgreSQL features (enums, gist exclusion constraints for overlapping reservations).
- [x] **Domain Entities:** `Building`, `Room`, `Reservation`, `User`, and `Amenities` configured with JPA.
- [x] **Infrastructure:** Dockerfiles, `docker-compose` setup, and basic GitHub Actions CI pipeline.
- [x] **Frontend Scaffold:** Angular 17 base configuration.

### ⏳ To Do (Planned Features)
- [ ] **REST API:** Implementation of controllers and services for Buildings, Rooms, and Reservations.
- [ ] **Security & Auth:** JWT authentication, Role-based access control (USER, MANAGER, ADMIN), and Google OAuth2 integration.
- [ ] **Frontend UI:** Routing, views for Building List, Room Details, Reservation Forms, and Admin Dashboard.
- [ ] **Notifications:** Email notification service for booking confirmations and cancellations.
- [ ] **Refactoring:** Resolve JPA entity mapping mismatches and complete DTO validations.
