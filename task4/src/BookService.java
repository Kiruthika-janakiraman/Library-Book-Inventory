import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class BookService {

    private final ArrayList<Book> books = new ArrayList<>();

    // Added for testability
    public List<Book> getBooks() {
        return books;
    }

    // Create - Add a new book
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully: " + book.getTitle());
    }

    // Read - Display all books
    public void getAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("List of all books:");
            books.forEach(this::displayBook);
        }
    }

    // Read - Find a book by ID
    public void getBookById(int id) {
        Optional<Book> foundBook = books.stream()
                .filter(b -> b.getId() == id)
                .findFirst();

        if (foundBook.isPresent()) {
            System.out.println("Book found:");
            displayBook(foundBook.get());
        } else {
            System.out.println("Book with ID " + id + " not found.");
        }
    }

    // Update - Update book details
    public void updateBook(int id, String title, String author, String isbn, int publicationYear) {
        Optional<Book> foundBook = books.stream()
                .filter(b -> b.getId() == id)
                .findFirst();

        if (foundBook.isPresent()) {
            Book book = foundBook.get();
            book.setTitle(title);
            book.setAuthor(author);
            book.setIsbn(isbn);
            book.setPublicationYear(publicationYear);

            System.out.println("Book updated successfully:");
            displayBook(book);
        } else {
            System.out.println("Book with ID " + id + " not found.");
        }
    }

    // Delete - Remove a book by ID
    public void deleteBook(int id) {
        Optional<Book> foundBook = books.stream()
                .filter(b -> b.getId() == id)
                .findFirst();

        if (foundBook.isPresent()) {
            Book book = foundBook.get();
            books.remove(book);
            System.out.println("Book deleted successfully: " + book.getTitle());
        } else {
            System.out.println("Book with ID " + id + " not found.");
        }
    }

    // Generic search method to reduce code duplication (DRY)
    private void searchBooks(Predicate<Book> condition, String notFoundMessage) {
        long count = books.stream()
                .filter(condition)
                .peek(this::displayBook)
                .count();

        if (count == 0) {
            System.out.println(notFoundMessage);
        }
    }

    // Search books by title
    public void searchBooksByTitle(String title) {
        searchBooks(
                b -> b.getTitle().toLowerCase().contains(title.toLowerCase()),
                "No books found with title containing '" + title + "'."
        );
    }

    // Search books by author
    public void searchBooksByAuthor(String author) {
        searchBooks(
                b -> b.getAuthor().toLowerCase().contains(author.toLowerCase()),
                "No books found with author containing '" + author + "'."
        );
    }

    // Search books by publication year
    public void searchBooksByPublicationYear(int publicationYear) {
        searchBooks(
                b -> b.getPublicationYear() == publicationYear,
                "No books found published in the year '" + publicationYear + "'."
        );
    }

    // Search books by ISBN
    public void searchBooksByIsbn(String isbn) {
        searchBooks(
                b -> b.getIsbn().equals(isbn),
                "No books found with ISBN '" + isbn + "'."
        );
    }

    // Display books by exact author name
    public void displayBooksByAuthor(String author) {
        searchBooks(
                b -> b.getAuthor().equalsIgnoreCase(author),
                "No books found by author '" + author + "'."
        );
    }

    // Common method to display book details
    private void displayBook(Book book) {
        System.out.println(
                "ID: " + book.getId()
                + ", Title: " + book.getTitle()
                + ", Author: " + book.getAuthor()
                + ", ISBN: " + book.getIsbn()
                + ", Publication Year: " + book.getPublicationYear()
        );
    }
}