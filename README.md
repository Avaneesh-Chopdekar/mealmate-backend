# MealMate Backend
Food Delivery Application by Avaneesh Chopdekar    
Version 1.0.0

### **Tech Stack**
- Spring Boot 3
  - Spring Security for Authentication
  - Spring Data JPA ORM
  - Spring Data Rest
  - Spring Validation
  - Spring Boot Actuator
- MySQL Database
- Swagger for API Documentation
- Cloudinary for Image Upload
- WebSocket for Order Tracking
- Stripe for Payment Integration
- Docker for Containerization
- IntelliJ IDEA for Development

### **Features**
- Authentication & Role-Based Access Control (RBAC)
- Restaurants & Menu Management
- Riders & Order Tracking (WebSocket)
- Stripe Payment Integration
- Image Upload (Cloudinary)
- API Documentation (Swagger)

---

## 🚀 **Run the Project**
You can run the project **with Docker** (recommended) or **without Docker**.

### **🔹 1. Clone the Repository**
```sh
git clone https://github.com/Avaneesh-Chopdekar/mealmate-backend.git
cd mealmate-backend
```

---

# 🐳 **Run with Docker (Recommended)**
### **📌 Prerequisites**
- **Docker** & **Docker Compose** installed

### **🔹 1. Set up the Environment Variables**
Rename the `.env.example` file to `.env` and update the required values:

```sh
cp .env.example .env
```

### **🔹 2. Build & Start Containers**
```sh
docker-compose --env-file .env up --build -d
```
This will:
✅ Start a **MySQL database** container  
✅ Start the **Spring Boot API** container

### **🔹 3. Access the API**
- API: `http://localhost:8081/api`
- Swagger Docs: `http://localhost:8081/api/docs`
- MySQL: `localhost:3307` (username & password from `.env`)

### **🔹 4. Stop Containers**
```sh
docker-compose down
```

---

# 💻 **Run without Docker**
### **📌 Prerequisites**
- **Java 23**
- **Maven**
- **MySQL 8**
- (Optional) **IntelliJ IDEA / VS Code**

### **🔹 1. Set Up MySQL Database**
Create a new database in MySQL:
```sql
CREATE DATABASE mealmate_db;
```
Then, update your `application.yaml` with the correct database credentials.

### **🔹 2. Set up Environment Variables**
Rename the `.env.example` file to `.env` and update the required values:

```sh
cp .env.example .env
```

### **🔹 3. Build & Run the App**
```sh
mvn clean install
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

Alternatively, if using **IntelliJ**, you can:
1. Open the project
2. Set environment variables
3. Click **Run ▶️**

### **🔹 4. Access the API**
- API: `http://localhost:8080/api`
- Swagger Docs: `http://localhost:8080/api/docs`

---

## 📝 **License**
[MIT License](https://github.com/Avaneesh-Chopdekar/mealmate-backend/blob/master/LICENSE)

---

## 🎯 **Contributing**
1. Fork the repo
2. Create a new branch (`git checkout -b feature-name`)
3. Commit your changes (`git commit -m "Added feature X"`)
4. Push the branch (`git push origin feature-name`)
5. Create a Pull Request 🚀

---
