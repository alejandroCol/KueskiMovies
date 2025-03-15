# Kueski Movies

## Description

Kueski Movies is an Android application developed in Kotlin that allows users to explore the most popular movies using TheMovieDB API. It implements Clean Architecture, modularization, and development best practices to ensure maintainability and scalability.

## Architecture

The application follows Clean Architecture, separating responsibilities into different layers:

- **Presentation**: Contains the UI built with Jetpack Compose and manages the application state.
- **Domain**: Defines use cases and business rules.
- **Data**: Handles data retrieval from the API and local storage.

The MVVM pattern is implemented to maintain a decoupled and scalable architecture.

## Key Features

- **Modularization**: The application is divided into modules to improve maintainability and scalability.
- **Pagination**: Uses Paging 3 library for efficient data loading.
- **Real-time search**: Implements local movie filtering with instant response.
- **Light/Dark Mode support**.
- **Multi-language support (Spanish/English)**.

## Security and Credential Management

- The API Key is securely stored in the `local.properties` file, preventing exposure in the source code.
- **GitHub Secrets** are used to configure sensitive variables in CI/CD.

## CI/CD

The application is configured with GitHub Actions for:

- **Unit test execution**.
- **Automatic build and distribution** to Firebase App Distribution.

## Installation and Execution

1. Clone the repository.
2. Add TheMovieDB API Key in `local.properties`:
MK_API_KEY= "YOUR KEY"
3. Run the application from Android Studio.

## Technologies Used

- **Kotlin**
- **Jetpack Compose**
- **Dagger Hilt** (dependency injection)
- **Retrofit** (API consumption)
- **Room Database** (local storage)
- **Paging 3** (data pagination)
- **Firebase App Distribution** (app distribution)

