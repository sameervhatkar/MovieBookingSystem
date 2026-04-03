# 🎬 Movie Booking System Backend (Spring Boot)

This project is a backend implementation of a Movie Booking System similar to platforms like **BookMyShow**.

It supports movie management, theatre setup, auditorium configuration, seat generation, show scheduling, seat locking with timeout, booking creation, payment simulation, and automatic seat release using a scheduler.

---

# 🚀 Tech Stack

- Java 22
- Spring Boot 4
- Spring Data JPA (Hibernate)
- MySQL
- Maven
- Lombok

---

# 📦 Features Implemented

## Admin Features

- Create Movie
- Update Movie
- Delete Movie
- Create Theatre
- Create Auditorium
- Bulk Seat Generation
- Create Show

---

## User Features

- Create User
- Update User

---

# 🎟 Seat Booking Flow

Automatic **ShowSeat generation** happens when a Show is created.

Seat availability lifecycle:

```
AVAILABLE → LOCKED → BOOKED
```

If payment is not completed within **5 minutes**:

```
LOCKED → AVAILABLE
```

Handled automatically using Spring Scheduler.

---

# 💳 Booking Flow

### Step 1 — Create Booking

```
POST /bookings
```

Locks seats for 5 minutes.

---

### Step 2 — Make Payment

```
POST /payments
```

Confirms booking and marks seats as BOOKED.

---

### Step 3 — Auto Release

Scheduler releases locked seats automatically if payment is not completed within timeout.

---

# ⏱ Scheduler Implementation

Seat unlock scheduler runs every **60 seconds**.

Enabled using:

```
@EnableScheduling
```

---

# 🗄 Database Entities

Core entities:

- User
- Movie
- Theatre
- Auditorium
- Seat
- Show
- ShowSeat
- Booking
- BookingSeat
- Payment

All entities extend:

```
BaseModel
```

Includes:

- id
- createdAt
- updatedAt
- createdBy
- updatedBy

---

# 🔄 Transaction Management

Critical booking operations use:

```
@Transactional
```

Ensures:

- atomic seat locking
- booking consistency
- payment integrity

---

# 📁 Project Architecture

Layered architecture:

```
Controller → Service → Repository → Database
```

DTO mapping handled using:

```
EntityToDTOMapper
```

Ensures entities are not exposed directly to clients.

---

# 🔐 Security Implementation

Spring Security has been integrated to protect application endpoints using **HTTP Basic Authentication**.

Currently implemented authentication flow:

```
Client sends username + password
↓
Spring Security intercepts request
↓
Custom UserDetailsService loads user from database
↓
Password verified using BCryptPasswordEncoder
↓
Access granted if credentials are valid
```

---

## Public Endpoints

The following endpoints are accessible without authentication:

```
POST /signup
POST /login
```

All other endpoints require authentication.

---

## Password Encryption

User passwords are securely stored using:

```
BCryptPasswordEncoder
```

This ensures passwords are never stored in plain text inside the database.

---

## Custom Authentication Components

Security configuration uses:

```
SecurityFilterChain
DaoAuthenticationProvider
Custom UserDetailsService
Custom UserDetails implementation
BCryptPasswordEncoder
```

Authentication logic is implemented inside:

```
SecurityConfiguration.java
MyUserDetailsService.java
MyUserDetails.java
```

---

## Protected Endpoints Example

Examples of secured endpoints:

```
POST /admin/movies
POST /admin/shows
POST /bookings
POST /payments
```

These endpoints require valid authentication credentials.

Example request header:

```
Authorization: Basic base64(username:password)
```

---

## Future Security Enhancements (Planned)

Planned upgrades:

```
JWT Authentication
Role-Based Authorization (Admin / User separation)
Refresh token support
Stateless session architecture
```

# ⚙️ How To Run The Project

### Step 1

Clone repository

```
git clone https://github.com/sameervhatkar/MovieBookingSystem.git
```

---

### Step 2

Create MySQL database

```
MovieBookingSystem
```

---

### Step 3

Configure application.properties

```
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

---

### Step 4

Run application

```
MovieBookingSystemApplication.java
```

Server starts at:

```
http://localhost:8080
```

---

# 📌 Example Booking API Sequence

```
POST /bookings
→ locks seats

POST /payments
→ confirms booking

Scheduler
→ releases expired locks automatically if payment not completed
```

---

# 👨‍💻 Author

**Sameer Vhatkar**

Backend Developer  
Java | Spring Boot | Data Structures & Algorithms