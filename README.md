# Clean Architecture Blueprint for Android Project Codebase

## Overview
Clean Architecture is a design principle that separates the concerns of an application into distinct layers, making it more modular, maintainable, and testable.

## Table of Contents
- [Modules](#modules)
  - [DI (Dependency Injection) Module](#di-dependency-injection-module)
  - [Data Module](#data-module)
    - [Local](#local)
    - [Mapper](#mapper)
    - [Remote](#remote)
    - [DTO (Data Transfer Objects)](#dto-data-transfer-objects)
    - [Repository](#repository)
  - [Utils](#utils)
- [Domain Module](#domain-module)
  - [Model](#model)
  - [Repository](#repository)
  - [UseCase](#usecase)
- [Presentation Module](#presentation-module)
  - [UI](#ui)
  - [ViewModel](#viewmodel)
  - [Utils](#utils)

---

## Modules

### DI (Dependency Injection) Module
The DI module handles dependency injection configuration for the application, and it leverages the Hilt Dependency Injection framework. Hilt is used to ensure that the different layers can communicate and access the necessary dependencies seamlessly. Hilt's integration in this module ensures that our application adheres to modern best practices for dependency injection in Android development.

### Data Module
The Data module is responsible for data-related operations. It provides data to the domain layer and manages data sources such as local databases and remote APIs. This module is divided into subpackages:

#### Local
The Local package contains components related to local data storage, such as databases or cache. This may include database entities, DAOs (Data Access Objects), and other local data management components.

#### Mapper
The Mapper package is responsible for mapping data between different layers of the application. It converts data from remote sources or local storage into domain models and vice versa.

#### Remote
The Remote package manages communication with remote data sources, such as web APIs. It includes networking components, API service interfaces, and data retrieval logic from remote sources.

#### DTO (Data Transfer Objects)
The DTO package contains Data Transfer Objects, which are used to represent data received from or sent to remote services. DTOs are used to decouple the external service's data format from the internal data model of the application.

#### Repository
The Repository package acts as an intermediary between the data sources (local and remote) and the domain layer. It provides a clean and consistent API for the domain layer to access and manipulate data.

### Utils
The Utils package contains error handling, data loading, and success wrapper classes for database and API related calls.

---

## Domain Module
The Domain module contains the core business logic and entities of the application. It defines the use cases and data models that are used by the application. This module is divided into subpackages:

### Model
The Model package contains the domain models, which represent the fundamental data structures used throughout the application's UI layer. These domain models play a pivotal role in decoupling the UI layer from DTOs or database entity models, ensuring a clean separation of concerns and reducing tight coupling with the UI layer.

### Repository
The Repository package in the domain layer defines the repository interfaces that are implemented in the data layer. These interfaces provide a clean and abstract way for the domain layer to access and manipulate data without directly depending on data sources.

### UseCase
The UseCase package contains the application's use cases, which represent high-level business operations. Use cases encapsulate the interaction between the presentation and data layers, orchestrating the flow of data and business logic. It's important to note that each function in a repository can be transformed into a corresponding use case, promoting a clear, structured separation of concerns and reusable functionality in our architecture.

---

## Presentation Module
The Presentation module is responsible for handling the user interface and interaction. It includes user interface components, ViewModels, and utility classes related to the presentation layer.

### UI
The UI package contains user interface components, including activities, fragments, views, widgets, and themes. This is where the visual representation of the application resides.

### ViewModel
The ViewModel package contains ViewModels, which are responsible for managing the UI-related data and state. ViewModels act as a bridge between the presentation layer and the domain layer.

### Utils
The Utils package includes utility/extension classes and helper functions that are used throughout the presentation layer. These utilities can help with common tasks such as data formatting, UI animations, or other UI-related functionality.
