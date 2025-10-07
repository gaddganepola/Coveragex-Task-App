# Coveragex Task App

This is a full-stack Dockerized application with:

* **Backend:** Spring Boot (Java 21, Maven)
* **Database:** MySQL 8
* **Frontend:** Static SPA served via Nginx

The application is fully containerized and can be run using Docker Compose.

---

## **Prerequisites**

Before running this project, make sure you have installed:

* [Docker](https://www.docker.com/get-started)
* [Docker Compose](https://docs.docker.com/compose/install/)
* (Optional) MySQL client if you want to connect directly

> **Note:** On Windows, avoid folder paths with spaces (e.g., rename `CoveragexTaskApp - Copy` to `CoveragexTaskApp_Copy`) to prevent Docker volume mount issues.

---

## **Project Structure**

```
CoveragexTaskApp/
│
├── src/              # Spring Boot source code
├── frontend/         # SPA frontend files (index.html, assets)
├── db/               # Optional init.sql (currently empty)
├── Dockerfile        # Multi-stage Dockerfile for Spring Boot
├── docker-compose.yml
└── nginx/
    └── default.conf  # Nginx configuration for SPA + API proxy
```

---

## **Step-by-Step Guide to Run the Project**

### **1. Clone the repository**

```bash
git clone https://github.com/yourusername/CoveragexTaskApp.git](https://github.com/gaddganepola/Coveragex-Task-App.git
cd CoveragexTaskApp
```

### **2. Start all containers**

```bash
docker-compose up -d
```

* `-d` runs the containers in detached mode (background).
* This will start **MySQL**, **Spring Boot app**, and **Nginx frontend**.

### **3. Verify containers are running**

```bash
docker ps
```

You should see something like:

```
CONTAINER ID   IMAGE                       PORTS                NAMES
xxxxxxx        mysql:8.0                   3307->3306          db
xxxxxxx        coveragextaskapp-copy-app   8080->8080          app
xxxxxxx        nginx:1.27-alpine           80->80              web
```

> **Note:** MySQL host port may be 3307 if 3306 is already in use on your system.

### **4. Access the application**

* **Frontend (SPA):** [http://localhost](http://localhost)
* **Backend API (via Nginx proxy):** [http://localhost/api](http://localhost/api)
* **Direct MySQL connection (optional):**

  ```
  Host: localhost
  Port: 3307
  User: app
  Password: app_pass
  Database: appdb
  ```

### **5. Test the application**

1. Open your browser at `http://localhost`. The frontend SPA should load.
2. Use a sample API endpoint (e.g., `/api/tasks`) to test backend connectivity.
3. Optional: Connect to the database using MySQL Workbench or DBeaver to verify tables are created.

### **6. Make changes and rebuild (if needed)**

#### Frontend

* Edit files in `frontend/`. Changes are reflected automatically in Nginx.
* Refresh the browser to see updates.

#### Backend

* Edit Spring Boot code in `backend/src/`.
* Rebuild and restart the app container:

```bash
docker-compose build app
docker-compose up -d
```

* View backend logs:

```bash
docker-compose logs -f app
```

### **7. Stop the application**

```bash
docker-compose down
```

* Add `-v` to remove volumes and reset database:

```bash
docker-compose down -v
```

---

## **Troubleshooting**

1. **Port conflicts:**

   * If 3306 (MySQL) or 8080 (Spring Boot) are in use, change the host port in `docker-compose.yml`.

2. **Windows path issues:**

   * Avoid spaces in folder names.
   * Use forward slashes `/` in volume paths.

3. **Database container fails:**

   * Remove old volume and restart:

```bash
docker-compose down -v
docker-compose up -d
```

---

## **Tech Stack**

* Java 21 + Spring Boot
* Maven
* MySQL 8
* Nginx 1.27-alpine
* Docker + Docker Compose

---

## **Author**

Dinethra Dayan
