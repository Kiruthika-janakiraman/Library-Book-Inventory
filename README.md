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
- `src/TestBookService.java`: Automated test script to verify application logic.

## Prerequisites
- Java Development Kit (JDK) 8 or higher.

## How to Compile and Run
Open your terminal/command prompt, navigate to the project directory, and run:
```bash
javac src/*.java
java -cp src Main
```

## How to Test
To verify the core functionality via the automated test script:
```bash
javac src/*.java
java -cp src TestBookService
```
