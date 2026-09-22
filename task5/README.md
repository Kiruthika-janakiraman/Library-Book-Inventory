# IWA Internship – Week 5 Library Book Inventory Management System

## Project purpose
This repository contains the Week 5 integrated Java application, deployment scripts, lightweight regression/integration checks, and developer documentation.

## Requirements
- JDK 11 or later for the supplied `jar --create --main-class` packaging command.
- A terminal/command prompt.
- No Maven or Gradle is required.

## Structure
```text
IWA_Week5_Library_Inventory/
├── src/
│   ├── Book.java
│   ├── BookService.java
│   ├── Main.java
│   └── IntegrationTest.java
├── scripts/
│   ├── build.sh
│   ├── run.sh
│   ├── test.sh
│   ├── build.bat
│   ├── run.bat
│   └── test.bat
├── dist/
├── docs/
└── README.md
```

## Build
Linux/macOS:
```bash
./scripts/build.sh
```
Windows:
```bat
scripts\build.bat
```

## Run
Linux/macOS:
```bash
./scripts/run.sh
```
Windows:
```bat
scripts\run.bat
```
or:
```bash
java -jar dist/library-inventory.jar
```

## Test
Linux/macOS:
```bash
./scripts/test.sh
```
Windows:
```bat
scripts\test.bat
```

## Deployment simulation
The build script compiles all Java sources into `build/classes` and packages them into:
`dist/library-inventory.jar`

The application is a console application and stores data in memory. Restarting the application resets the sample/demo inventory. A future production version should introduce persistent storage such as a relational database or a file-based repository.

## Maintenance notes
The service layer owns business rules, while `Main` handles console interaction. New persistence, REST APIs, authentication, logging, or configuration should be added behind clear interfaces rather than embedded in the menu logic.
