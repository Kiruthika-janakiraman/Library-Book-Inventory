# Week 3 Testing and Debugging Report

## 1. Project Overview
This project is an extension of the Week 2 Library Book Inventory Management System. The primary objective for Week 3 was to introduce comprehensive automated testing using JUnit 5 and to practice identifying, debugging, and resolving software defects. The application manages a collection of books with functionalities to add, list, search, update, and delete books through a console-based interface.

## 2. Testing Approach
To thoroughly test the application while maintaining its existing structure (minimizing refactoring), the following approach was taken:
- **JUnit 5 Standalone:** Used the JUnit 5 platform console standalone JAR to run tests without introducing complex build tools like Maven or Gradle.
- **Minimal Refactoring:** Added a `getBooks()` method to `BookService.java` to allow the test class to inspect the internal list state directly.
- **Output Stream Interception:** To test methods that print output directly to the console (e.g., search methods), `System.out` was temporarily redirected to a `ByteArrayOutputStream` during test execution, allowing assertions on the printed text.

## 3. Test Cases
The `BookServiceTest` class includes 16 test cases covering all CRUD operations and edge cases:
- `testAddBook()`: Validates that a new book is added to the list.
- `testGetAllBooks_Empty()`: Validates correct output when inventory is empty.
- `testGetAllBooks_WithBooks()`: Validates listing multiple books.
- `testGetBookById_Exists()` / `testGetBookById_NotExists()`: Validates fetching existing and non-existing IDs.
- `testUpdateBook_Exists()` / `testUpdateBook_NotExists()`: Validates property updates and missing book handling.
- `testDeleteBook_Exists()` / `testDeleteBook_NotExists()`: Validates removal of books and list size reduction.
- `testSearchBooksByTitle_ExistsCaseInsensitive()` / `testSearchBooksByTitle_NotExists()`: Validates case-insensitive title search.
- `testSearchBooksByAuthor_ExistsCaseInsensitive()` / `testSearchBooksByAuthor_NotExists()`: Validates case-insensitive author search.
- `testSearchBooksByPublicationYear_Exists()`: Validates search by publication year.
- `testSearchBooksByIsbn_Exists()`: Validates exact ISBN search.
- `testDisplayBooksByAuthor_ExactMatch()`: Validates exact author display.

## 4. Initial Test Results
Initially, after constructing the tests against the correct logic, all 16 tests executed and passed, confirming baseline functionality.

## 5. Bugs Identified (Intentional Introduction)
To simulate a real-world debugging scenario, two bugs were intentionally introduced into `BookService.java`:
1. **Logic Error (Update):** In `updateBook`, the publication year was set as `book.setPublicationYear(publicationYear + 1);` instead of the provided year.
2. **Case-Sensitivity Error (Search):** In `searchBooksByTitle`, `.toLowerCase()` was removed from the comparison: `if (book.getTitle().contains(title))`.

## 6. Debugging Strategy
When running the test suite after introducing the bugs, the following failures occurred:
- `testUpdateBook_Exists()` failed with `org.opentest4j.AssertionFailedError: expected: <1950> but was: <1951>`
- `testSearchBooksByTitle_ExistsCaseInsensitive()` failed with `org.opentest4j.AssertionFailedError: expected: <true> but was: <false>` (indicating the expected book was not printed to output).

**Strategy:**
- The assertion error in `updateBook` clearly pointed to an off-by-one error in the publication year assignment. I inspected the `updateBook` method and located the erroneous `+ 1`.
- The failure in the case-insensitive title search test (`assertTrue` on the captured output stream) indicated that searching for "effective" did not find "Effective Java". I inspected `searchBooksByTitle` and realized the strings were being compared exactly, missing the `.toLowerCase()` normalization.

## 7. Bug Fixes
The fixes applied to `BookService.java`:
- **Fix 1:** Reverted `book.setPublicationYear(publicationYear + 1);` back to `book.setPublicationYear(publicationYear);`.
- **Fix 2:** Reverted the `if` condition in `searchBooksByTitle` back to `if (book.getTitle().toLowerCase().contains(title.toLowerCase()))`.

## 8. Final Test Results
After applying the bug fixes, the JUnit 5 test suite was re-run. All 16 tests passed successfully (0 failures, 0 errors, 16 successful), validating that the core functionality was restored to the expected state.

## 9. Conclusion
The Week 3 objectives were successfully met. The project now boasts a robust suite of JUnit 5 tests covering happy paths and edge cases, ensuring the stability of the inventory management system. The debugging exercise demonstrated the value of automated tests in quickly identifying regressions and logic errors.
