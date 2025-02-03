# Mancala Game Implementation

This project is my object-oriented implementation of the **Mancala** game, built with the following technologies:

- **Java** for the domain logic and API
- **MongoDB** for persistence
- **React** for the front-end user interface

The application is split into two main servers:
1. **Backend Server** (Java-based)
2. **Frontend Server** (React-based)

## Getting Started

This project has a DockerFile which makes it easy to run, build and run the Dockerfile using:
```bash
sudo docker compose build;
sudo docker compose up
```

Alternatively, you can manually start each part of the application:

To get the application up and running, follow the steps below for each server. Ensure you have all the required dependencies installed.

#### 1. **Start MongoDB**

MongoDB is used as the database for storing game data. To start MongoDB, use the following command:

```bash
sudo systemctl start mongodb
```
#### 2. **Run the Backend Server**
The backend is built with Java and Gradle. To start the backend server, run:
```bash
./gradlew run
```
This will start the backend API that communicates with MongoDB and handles game logic.

#### 3. **Run the Frontend Server**
The frontend is built with React and communicates with the backend via HTTP requests. To start the front-end server, navigate to the client directory and run:
```bash
cd client;
npm install;
npm run dev
```
This will start the development server for the React app, and you should be able to access the application in your browser.

## Technologies Used

- **Java**: The core game logic and API endpoints
- **MongoDB**: Persisting game state
- **React**: The user interface
- **Gradle**: Managing the backend build and dependencies
- **npm**: Managing the frontend build and dependencies

## Folder Structure

- `domain/` – Contains the backend code (Java, Gradle)
- `client/` – Contains the frontend code (React, npm)
- `persistence/` – Contains MongoDB data (if applicable)
- `api/` – Contains the API layer which connects domain and persistence to Client

