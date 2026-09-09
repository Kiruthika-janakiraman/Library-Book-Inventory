# Library Book Inventory Management

A robust Java command-line application to efficiently manage a library's book inventory. This application uses an in-memory data structure to handle books and provides a user-friendly text interface for performing standard inventory operations.

## Features
- **Create (Add Book)**: Add new books with ID, Title, Author, ISBN, and Publication Year.
- **Read (List & Search)**: View all books or search for specific books using ID, Title, Author, Publication Year, or ISBN.
- **Update**: Modify existing book details.
- **Delete**: Remove books from the inventory.
- **Input Validation**: Automatically prevents empty inputs for text fields and validates that the publication year is realistic (e.g., up to the current year).

## Project Structure
- `src/Book.java`: The core model representing a book.
- `src/BookService.java`: The service layer containing the business logic (CRUD operations).
- `src/Main.java`: The entry point and interactive user interface loop.
- `src/BookServiceTest.java`: Comprehensive JUnit 5 automated tests.
- `lib/`: Contains the standalone JUnit 5 JAR file.

## Prerequisites
- Java Development Kit (JDK) 8 or higher.

## How to Compile and Run
Open your terminal/command prompt, navigate to the project directory, and run:
```bash
javac src/*.java
java -cp src Main
```

## How to Test
The project uses JUnit 5 for testing. The standalone test runner is located in the `lib` folder.
To compile and run the test suite:
```bash
# Navigate to src directory
cd src

# Compile the tests
javac -cp ".;..\lib\junit-platform-console-standalone-1.10.0.jar" Book.java BookService.java BookServiceTest.java

# Run the tests
java -jar ..\lib\junit-platform-console-standalone-1.10.0.jar -cp . --scan-class-path
```
