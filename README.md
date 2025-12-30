# 🚀 Task Management API (Spring Boot)

A robust and scalable RESTful API designed to manage task lists and their associated tasks. Built with **Spring Boot 4**, **Java 21**, and **PostgreSQL**, this project demonstrates a professional approach to API design, including DTO mapping, global exception handling, and containerized database management.

source : https://youtu.be/brnazVsG4cY?si=UQITeNbKIaF-SaJa  

---

## 🛠 Tech Stack

* **Backend:** Java 21 & Spring Boot 4.0.1
* **Database:** PostgreSQL (Production) & H2 (Testing)
* **Data Access:** Spring Data JPA (Hibernate)
* **Containerization:** Docker & Docker Compose
* **Build Tool:** Maven
* **Architecture:** Layered Architecture (Controller, Service, Domain, Mapper)

---

## ✨ Key Features

* **Hierarchical Structure:** Manage multiple Task Lists, each containing its own set of Tasks.
* **UUID Based IDs:** Enhanced security and scalability using UUIDs instead of sequential integers.
* **Global Exception Handling:** Centralized error management for clean and consistent API responses.
* **Data Transformation:** Separation of concerns using DTOs (Data Transfer Objects) and Mappers.
* **Database Versioning:** Automated schema updates using Hibernate `ddl-auto`.

---

## 🚦 API Endpoints

### 📂 Task Lists
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/task-lists` | Retrieve all task lists |
| `POST` | `/api/task-lists` | Create a new task list |
| `GET` | `/api/task-lists/{id}` | Get a specific task list by UUID |
| `PUT` | `/api/task-lists/{id}` | Update an existing task list |
| `DELETE` | `/api/task-lists/{id}` | Delete a task list |

### 📝 Tasks (Nested)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/task-lists/{list_id}/tasks` | Get all tasks within a specific list |
| `POST` | `/api/task-lists/{list_id}/tasks` | Create a task under a specific list |
| `GET` | `/api/task-lists/{list_id}/tasks/{task_id}` | Get specific task details |
| `PUT` | `/api/task-lists/{list_id}/tasks/{task_id}` | Update task details |
| `DELETE` | `/api/task-lists/{list_id}/tasks/{task_id}` | Delete a task |

---

## ⚙️ Getting Started

### Prerequisites
* **JDK 21** or higher
* **Maven** 3.x
* **Docker Desktop** (for database)

### Setup & Installation

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/Omar-Ahmeed/task-app.git](https://github.com/Omar-Ahmeed/task-app.git)
    cd task-app
    ```

2.  **Start the PostgreSQL Database:**
    The project includes a `docker-compose.yml` file to spin up the database instantly.
    ```bash
    docker-compose up -d
    ```

3.  **Configure Environment:**
    Ensure `src/main/resources/application.properties` matches your local/Docker DB credentials (default is set to PostgreSQL on port 5432).

4.  **Run the Application:**
    ```bash
    mvn spring-boot:run
    ```

The API will be available at: `http://localhost:8080`

---

## ⚠️ Error Handling
The API returns consistent error structures. For example, if an invalid argument is passed:
```json
{
  "status": 400,
  "message": "Error message details",
  "path": "/api/task-lists/..."
}
