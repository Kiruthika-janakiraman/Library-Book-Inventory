import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Business/service layer for the Library Book Inventory Management System.
 * The current implementation intentionally uses an in-memory ArrayList.
 */
public class BookService {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null.");
        }
        if (getBookById(book.getId()).isPresent()) {
            throw new IllegalArgumentException("Book ID already exists: " + book.getId());
        }
        books.add(book);
        System.out.println("Book added successfully.");
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        books.forEach(book -> {
            System.out.println("------------------------------");
            System.out.println(book);
        });
        System.out.println("------------------------------");
    }

    public Optional<Book> getBookById(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst();
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    private void searchBooks(Predicate<Book> condition, String notFoundMessage) {
        List<Book> matches = books.stream()
                .filter(condition)
                .collect(Collectors.toList());

        if (matches.isEmpty()) {
            System.out.println(notFoundMessage);
            return;
        }
        matches.forEach(book -> {
            System.out.println("------------------------------");
            System.out.println(book);
        });
        System.out.println("------------------------------");
    }

    public void searchBooksByTitle(String title) {
        String query = requireText(title, "Title");
        searchBooks(
            book -> book.getTitle().toLowerCase().contains(query.toLowerCase()),
            "No books found with title containing '" + query + "'."
        );
    }

    public void searchBooksByAuthor(String author) {
        String query = requireText(author, "Author");
        searchBooks(
            book -> book.getAuthor().toLowerCase().contains(query.toLowerCase()),
            "No books found with author containing '" + query + "'."
        );
    }

    public void searchBooksByPublicationYear(int year) {
        searchBooks(
            book -> book.getPublicationYear() == year,
            "No books found for publication year " + year + "."
        );
    }

    public void searchBooksByIsbn(String isbn) {
        String query = requireText(isbn, "ISBN");
        searchBooks(
            book -> book.getIsbn().equals(query),
            "No books found with ISBN '" + query + "'."
        );
    }

    public void displayBooksByAuthor(String author) {
        String query = requireText(author, "Author");
        searchBooks(
            book -> book.getAuthor().equalsIgnoreCase(query),
            "No books found by author '" + query + "'."
        );
    }

    public void updateBook(int id, String title, String author, String isbn, int publicationYear) {
        Optional<Book> foundBook = getBookById(id);
        if (!foundBook.isPresent()) {
            System.out.println("Book with ID " + id + " not found.");
            return;
        }

        Book book = foundBook.get();
        book.setTitle(requireText(title, "Title"));
        book.setAuthor(requireText(author, "Author"));
        book.setIsbn(requireText(isbn, "ISBN"));
        book.setPublicationYear(publicationYear);
        System.out.println("Book updated successfully.");
    }

    public void deleteBook(int id) {
        Optional<Book> foundBook = getBookById(id);
        if (!foundBook.isPresent()) {
            System.out.println("Book with ID " + id + " not found.");
            return;
        }

        Book book = foundBook.get();
        books.remove(book);
        System.out.println("Book deleted successfully: " + book.getTitle());
    }

    private String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }
}
