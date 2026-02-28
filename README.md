# 🏛 Decentralized Complaint Redressal System (Backend)

A production-style backend system built using **Spring Boot** for handling citizen complaints with automated routing, SLA tracking, multi-level escalation, audit logging, and analytics.

---

## 🚀 Project Overview

This system allows citizens to raise complaints related to public infrastructure (Road, Water, Electricity, etc.), and ensures:

- Automatic department routing
- SLA-based monitoring
- Multi-level escalation
- Role-based workflow control
- Full audit tracking
- Operational analytics

Built as an enterprise-style backend architecture.

---

## 🛠 Tech Stack

- Java 17
- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA
- MySQL
- Lombok
- Swagger (OpenAPI)
- Scheduler (@Scheduled)

---

## 🔐 Authentication & Security

- JWT-based authentication
- BCrypt password hashing
- Role-based access control:
  - CITIZEN
  - OFFICER
  - SUPER_ADMIN
- Method-level security using `@PreAuthorize`

---

## 🏗 System Architecture

Layered architecture:
Controller → Service Interface → Service Implementation → Repository → Database


Additional Layers:
- Scheduler (SLA Engine)
- Audit Logging
- Escalation Engine
- Analytics Module

---

## 🧾 Core Features

### 1️⃣ Complaint Management
- Create complaint
- Geo-based ward detection (Latitude & Longitude)
- Department auto-assignment
- Unique complaint ID generation
- Status workflow:NEW → ASSIGNED → IN_PROGRESS → RESOLVED → CLOSED

  
---

### 2️⃣ SLA-Based Escalation Engine

- SLA deadline stored in DB
- Background scheduler runs every 1 minute
- Automatic escalation if SLA breached
- Multi-level escalation:
- Level 1 → Department Admin
- Level 2 → Super Admin
- Escalation history tracking

---

### 3️⃣ Audit Logging

Tracks:
- Previous status
- New status
- Updated by
- Timestamp

Ensures transparency and traceability.

---

### 4️⃣ Analytics Module

Provides operational insights:

- Complaints count per department
- Complaints count per ward
- Escalation rate (%)
- Average resolution time (in hours)

Endpoints:
GET /api/v1/analytics/department-summary
GET /api/v1/analytics/ward-summary
GET /api/v1/analytics/escalation-rate
GET /api/v1/analytics/avg-resolution-time


---

## 📌 API Documentation

Swagger UI available at:
http://localhost:8080/swagger-ui/index.html


---

## 🗄 Database Models

- User
- Department
- Ward
- Complaint
- AuditLog
- Escalation

---

## 🔄 Escalation Flow Example

1. Citizen raises complaint
2. SLA deadline calculated
3. If not resolved within SLA:
   - Escalates to Department Admin
4. If still unresolved:
   - Escalates to Super Admin
5. All actions recorded in AuditLog

---

## 📈 Example Escalation Response

```json
[
  {
    "escalationId": 1,
    "escalatedTo": "officer@test.com",
    "reason": "SLA breached - Level 1",
    "timestamp": "2026-02-28T11:14:45"
  }
]
