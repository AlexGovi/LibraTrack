# 📚 Library Management System - Spring Boot

A Library Management System built using **Spring Boot**, secured with **JWT**, and powered by **MySQL**. This project allows users to issue and return books, while admins can manage the system. Authentication and role-based access are handled using **Spring Security**.

---

## 🚀 Features

- ✅ **User & Admin Login** with JWT-based authentication
- 📖 **Issue Book** with due date tracking
- 🔁 **Return Book**
- 🔐 **Secure API access** using JWT
- 👤 **Role-based Access Control** using Spring Security

---

## 🛠️ Tech Stack

| Layer        | Technology                     |
|--------------|--------------------------------|
| Backend      | Spring Boot, Spring Data JPA   |
| Security     | Spring Security, JWT           |
| Database     | MySQL                          |
| Testing Tool | Postman                        |

---

## ⚙️ Getting Started

### 1. Clone the Repository


git clone https://github.com/AlexGovi/library-management-system.git
cd library-management-system

### 2. Configure the Database
Create a MySQL database (e.g., library_db)

Update application.properties or application.yml with your DB credentials:


- spring.datasource.url=jdbc:mysql://localhost:3306/library_db
- spring.datasource.username=your_username
- spring.datasource.password=your_password
- spring.jpa.hibernate.ddl-auto=update


### 3. Run the Application
You can run the app using your IDE or via command line:


./mvnw spring-boot:run
🔐 Authentication Flow
Login using your credentials to receive a JWT token.

Use the token in Postman or any client as:


Authorization: Bearer <your-jwt-token>
## 📘 API Endpoints

### 🔐 Authentication

| Method | Endpoint        | Description             | Access |
|--------|-----------------|-------------------------|--------|
| POST   | `/auth/login`   | Login (User/Admin)      | Public |
| POST   | `/auth/register`| Register a new user     | Public |

---

### 👤 User APIs

| Method | Endpoint                  | Description                             | Access |
|--------|---------------------------|-----------------------------------------|--------|
| POST   | `/books/issue/{bookId}`   | Issue a book                            | User   |
| POST   | `/books/return/{bookId}`  | Return a previously issued book         | User   |



---

### 🛠️ Admin APIs

| Method | Endpoint             | Description                    | Access |
|--------|----------------------|--------------------------------|--------|
| POST   | `/admin/books`       | Add a new book                 | Admin  |
| PUT    | `/admin/books/{id}`  | Update book information        | Admin  |
| DELETE | `/admin/books/{id}`  | Delete a book from the system  | Admin  |
| GET    | `/books`             | Get list of all books          | Admin  |
| GET    | `/books/{id}`        | Get details of a book          | Admin  |


---




More endpoints can be added based on roles and use cases.

📫 Postman Collection
A Postman collection with all endpoints and sample JWT tokens is available in the postman/ folder of the repository (or [add link here]).

🙌 Contributing
Feel free to fork the repo, raise issues, or submit PRs to improve this project.


```bash
