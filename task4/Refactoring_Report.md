# Code Refactoring and Optimization Report
**Project:** Library Book Inventory
**Focus:** Code readability, DRY principles, Modularization, and Performance enhancements using Java Streams.

## 1. Overview
This report details the refactoring process for the Library Book Inventory application. The primary classes modified were `BookService.java` and `Main.java`. The goals were to enhance code structure, improve readability, modularize complex components, and leverage modern Java features (Java 8 Streams and Functional Interfaces) to eliminate redundancies and improve execution flow.

## 2. Refactoring Details & Before-and-After Comparisons

### 2.1 Implementing DRY in `BookService.java` Search Operations
**Issue:** The application originally had multiple search methods (`searchBooksByTitle`, `searchBooksByAuthor`, `searchBooksByPublicationYear`, `searchBooksByIsbn`, `displayBooksByAuthor`) that virtually duplicated the exact same loop structure, boolean flags, and print statements. This violated the DRY (Don't Repeat Yourself) principle and made maintenance harder.
**Solution:** We created a private, reusable `searchBooks(Predicate<Book> condition, String notFoundMessage)` method. This uses Java 8 Streams to filter the list based on a functional `Predicate`, reducing duplication significantly.

**Before:**
```java
// Repetitive logic repeated across 5 different methods
public void searchBooksByTitle(String title) {
    boolean found = false;
    for (Book book : books) {
        if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
            displayBook(book);
            found = true;
        }
    }
    if (!found) {
        System.out.println("No books found with title containing '" + title + "'.");
    }
}
```

**After:**
```java
// Reusable method
private void searchBooks(Predicate<Book> condition, String notFoundMessage) {
    long count = books.stream()
            .filter(condition)
            .peek(this::displayBook)
            .count();
    if (count == 0) {
        System.out.println(notFoundMessage);
    }
}

// Search method is now a concise 1-liner implementation
public void searchBooksByTitle(String title) {
    searchBooks(
            b -> b.getTitle().toLowerCase().contains(title.toLowerCase()),
            "No books found with title containing '" + title + "'."
    );
}
```
**Enhancement:** 
- **Readability & Maintenance:** Search logic is centralized. If the searching mechanism changes in the future, only the `searchBooks` method needs updating.
- **Lines of Code:** We've drastically reduced the line count in `BookService.java`, making it easier to parse.

### 2.2 Using `Optional` and Streams for CRUD Operations
**Issue:** Methods like `getBookById`, `updateBook`, and `deleteBook` relied on traditional `for` loops and manual indexing, which can be prone to off-by-one errors or cumbersome nested blocks.
**Solution:** We used the Stream API and `Optional<Book>` to find objects, improving null-safety and declarative clarity.

**Before:**
```java
public void deleteBook(int id) {
    for (int i = 0; i < books.size(); i++) {
        if (books.get(i).getId() == id) {
            Book deletedBook = books.remove(i);
            System.out.println("Book deleted successfully: " + deletedBook.getTitle());
            return;
        }
    }
    System.out.println("Book with ID " + id + " not found.");
}
```

**After:**
```java
public void deleteBook(int id) {
    Optional<Book> foundBook = books.stream().filter(b -> b.getId() == id).findFirst();

    if (foundBook.isPresent()) {
        Book book = foundBook.get();
        books.remove(book);
        System.out.println("Book deleted successfully: " + book.getTitle());
    } else {
        System.out.println("Book with ID " + id + " not found.");
    }
}
```
**Enhancement:** The `Optional` type safely handles cases where the book is not found without relying on explicit loop indexing and early `return` statements, resulting in a safer, more standard Java implementation.

### 2.3 Modularization of `Main.java`
**Issue:** The `main` method handled user input, switch-case routing, the text UI output, and specific logic like grabbing input variables for object creation. This resulted in a bloated, 200+ line function.
**Solution:** We extracted the console output into `displayMenu()` and moved the large data gathering cases into separate sub-handlers (`handleAddBook()`, `handleUpdateBook()`).

**Enhancement:** 
- **Structure:** The `while` loop within `main()` is now compact, making the primary execution loop of the application immediately obvious to readers.
- **Delegation of Responsibility:** The switch statement now merely routes traffic, delegating input gathering and object creation to helper methods.

## 3. Performance & Structural Conclusion
The refactoring process achieved its goal of bringing the codebase up to modern Java standards. While overall computational time remains relatively unchanged for small datasets, the code is now far more **readable, scalable, and idiomatic**:
1. **DRY Principle:** Removing redundant loop mechanics saved dozens of lines of code.
2. **Java Streams API:** Provided a functional, declarative approach that explicitly defines *what* the application does, rather than *how* it loops.
3. **Modularization:** Centralizing UI code allows developers to quickly skim structural routing logic separately from specific operation logic.
