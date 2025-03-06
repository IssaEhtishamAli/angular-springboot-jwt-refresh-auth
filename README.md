Angular + Spring Boot JWT Authentication with Refresh Token

A full-stack authentication system using Angular and Spring Boot, implementing JWT authentication with refresh tokens. The backend (Spring Boot) provides secure REST APIs, and the frontend (Angular) consumes those APIs for user authentication and authorization.

🚀 Features

User Registration & Login with JWT authentication

Access & Refresh Token management

Angular HTTP Interceptors for token handling

Secure API Endpoints

🖼️ Screenshots

🔹 Sign-Up Page
![Image](https://github.com/user-attachments/assets/ef9cff89-1227-4706-bbb8-ee9a8b0aa4a5)


🔹 Home Page (Users List)

![Image](https://github.com/user-attachments/assets/e5a705ae-4279-450d-a8d9-1e71bc158370)

🏗️ Tech Stack

🔹 Backend (Spring Boot)

Spring Boot 3.2.5

Spring Security & JWT

Jdbctemplate (PostgreSQL)

Java 21

Lombok

🔹 Frontend (Angular)

Angular 18

Bootstrap 5

RxJS & Interceptors

🛠️ Installation

🔹 Backend (Spring Boot)

# Clone the repository
git clone https://github.com/IssaEhtishamAli/angular-springboot-jwt-refresh-auth.git
cd angular-springboot-jwt-refresh-auth/backend

# Configure PostgreSQL database in application.properties

# Run the Spring Boot application
mvn spring-boot:run

🔹 Frontend (Angular)

cd angular-springboot-jwt-refresh-auth/frontend

# Install dependencies
npm install

# Start the Angular application
ng serve
