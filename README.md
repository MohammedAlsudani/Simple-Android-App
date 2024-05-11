
# Simple Android App

This repository contains a simple Android application built with the Model-View-ViewModel (MVVM) architecture, following best practices and employing clean architecture principles. The app utilizes XML for layout design, incorporates data binding for efficient UI updates, and integrates Hilt for dependency injection. Additionally, Coil library is utilized for efficient image loading and displaying.

## Features

- **MVVM Architecture**: Separation of concerns with clear distinction between business logic and UI components.
- **Clean Architecture**: Modular design promoting scalability, testability, and maintainability.
- **Data Binding**: Efficient binding of UI components to data sources for seamless updates.
- **Hilt Dependency Injection**: Simplified dependency injection for improved code readability and maintainability.
- **Coil Image Loading**: Fast and efficient image loading and displaying library for smooth user experience.

## Project Structure

The project is organized into the following packages:

- `adapter`: Contains RecyclerView adapters.
- `base`: Includes base classes.
- `model`: Houses data models and repositories.
- `viewBinding`: Handles view binding classes.
- `viewModel`: Houses view model classes.
- `MainActivity.kt`: Entry point activity of the application.
- `SimpleMvvmApplication.kt`: Application class for initializing dependencies and configurations.

## Setup

To run the project, follow these steps:

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the application on an emulator or physical device.

## Contributing

Contributions are welcome! If you'd like to contribute to this project, please fork the repository and create a pull request with your proposed changes. Ensure that your code adheres to the project's coding standards and practices.

## License

This project is licensed under the [MIT License](LICENSE)
