# API Automation Framework - Reqres.in

## Overview
This project is designed to automate API testing scenarios for the **Reqres.in** service. It covers user creation, retrieval, and updates using **RestAssured** and **TestNG**.

---

## Tech Stack
- **RestAssured** - API testing library
- **TestNG** - Test execution and reporting
- **Maven** - Dependency management
- **Java** - Programming language
- **Git** - Version control

---

## Prerequisites
- **Java Development Kit (JDK)** (Version 8 or higher)
- **Maven** (Ensure Maven is installed and configured)
- **IDE** (IntelliJ IDEA or Eclipse)
- **Git** (For cloning and version control)

---

## Features
- **User Management Scenarios:**
    1. **Create a User:**
        - Sends a POST request with user details (name, job).
        - Validates user creation in the response.
    2. **Retrieve a User:**
        - Sends a GET request using user ID.
        - Validates that user details match the created user.
    3. **Update a User:**
        - Sends a PUT request with updated details.
        - Confirms the update in the response.

---

## Installation
### 1. Clone the Repository
Ensure Git is installed, then run:
```
git clone https://github.com/ttohamy/reqres-api-automation.git
```

### 2. Download Dependencies
Navigate to the project directory and execute:
```
mvn clean install
```
---

## How to Run Tests
### Option 1: Using Maven
```
mvn clean test
```

### Option 2: Using IDE
1. Open the project in your IDE.
2. Locate the **TestNG.xml** file.
3. Right-click and select **Run**.

---