import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest {

    private BookService bookService;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        bookService = new BookService();
        System.setOut(new PrintStream(outContent));
    }

    // Helper method to reset output stream for cleaner assertions
    private void resetOutput() {
        outContent.reset();
    }

    @Test
    public void testAddBook() {
        Book book = new Book(1, "The Hobbit", "J.R.R. Tolkien", "9780261102217", 1937);
        bookService.addBook(book);

        List<Book> books = bookService.getBooks();
        assertEquals(1, books.size(), "Book list should have 1 item");
        assertEquals("The Hobbit", books.get(0).getTitle());
        assertTrue(outContent.toString().contains("Book added successfully: The Hobbit"));
    }

    @Test
    public void testGetAllBooks_Empty() {
        bookService.getAllBooks();
        assertTrue(outContent.toString().contains("No books available."));
    }

    @Test
    public void testGetAllBooks_WithBooks() {
        bookService.addBook(new Book(1, "1984", "George Orwell", "12345", 1949));
        bookService.addBook(new Book(2, "Animal Farm", "George Orwell", "67890", 1945));
        resetOutput();

        bookService.getAllBooks();
        assertTrue(outContent.toString().contains("List of all books:"));
        assertTrue(outContent.toString().contains("1984"));
        assertTrue(outContent.toString().contains("Animal Farm"));
    }

    @Test
    public void testGetBookById_Exists() {
        bookService.addBook(new Book(1, "1984", "George Orwell", "12345", 1949));
        resetOutput();

        bookService.getBookById(1);
        assertTrue(outContent.toString().contains("Book found:"));
        assertTrue(outContent.toString().contains("1984"));
    }

    @Test
    public void testGetBookById_NotExists() {
        bookService.getBookById(999);
        assertTrue(outContent.toString().contains("Book with ID 999 not found."));
    }

    @Test
    public void testUpdateBook_Exists() {
        bookService.addBook(new Book(1, "1984", "Old Author", "12345", 1949));
        resetOutput();

        bookService.updateBook(1, "1984", "George Orwell", "11111", 1950);
        
        List<Book> books = bookService.getBooks();
        assertEquals("George Orwell", books.get(0).getAuthor());
        assertEquals("11111", books.get(0).getIsbn());
        assertEquals(1950, books.get(0).getPublicationYear());
        assertTrue(outContent.toString().contains("Book updated successfully:"));
    }

    @Test
    public void testUpdateBook_NotExists() {
        bookService.updateBook(999, "Title", "Author", "ISBN", 2000);
        assertTrue(outContent.toString().contains("Book with ID 999 not found."));
    }

    @Test
    public void testDeleteBook_Exists() {
        bookService.addBook(new Book(1, "1984", "George Orwell", "12345", 1949));
        assertEquals(1, bookService.getBooks().size());
        resetOutput();

        bookService.deleteBook(1);
        assertEquals(0, bookService.getBooks().size());
        assertTrue(outContent.toString().contains("Book deleted successfully: 1984"));
    }

    @Test
    public void testDeleteBook_NotExists() {
        bookService.deleteBook(999);
        assertTrue(outContent.toString().contains("Book with ID 999 not found."));
    }

    @Test
    public void testSearchBooksByTitle_ExistsCaseInsensitive() {
        bookService.addBook(new Book(1, "Effective Java", "Joshua Bloch", "123", 2018));
        resetOutput();

        bookService.searchBooksByTitle("effective");
        assertTrue(outContent.toString().contains("Effective Java"));
    }

    @Test
    public void testSearchBooksByTitle_NotExists() {
        bookService.searchBooksByTitle("Nonexistent");
        assertTrue(outContent.toString().contains("No books found with title containing 'Nonexistent'."));
    }

    @Test
    public void testSearchBooksByAuthor_ExistsCaseInsensitive() {
        bookService.addBook(new Book(1, "Effective Java", "Joshua Bloch", "123", 2018));
        resetOutput();

        bookService.searchBooksByAuthor("joshua");
        assertTrue(outContent.toString().contains("Joshua Bloch"));
    }

    @Test
    public void testSearchBooksByAuthor_NotExists() {
        bookService.searchBooksByAuthor("Unknown");
        assertTrue(outContent.toString().contains("No books found with author containing 'Unknown'."));
    }

    @Test
    public void testSearchBooksByPublicationYear_Exists() {
        bookService.addBook(new Book(1, "Effective Java", "Joshua Bloch", "123", 2018));
        resetOutput();

        bookService.searchBooksByPublicationYear(2018);
        assertTrue(outContent.toString().contains("Effective Java"));
    }

    @Test
    public void testSearchBooksByIsbn_Exists() {
        bookService.addBook(new Book(1, "Effective Java", "Joshua Bloch", "12345", 2018));
        resetOutput();

        bookService.searchBooksByIsbn("12345");
        assertTrue(outContent.toString().contains("Effective Java"));
    }

    @Test
    public void testDisplayBooksByAuthor_ExactMatch() {
        bookService.addBook(new Book(1, "Effective Java", "Joshua Bloch", "123", 2018));
        resetOutput();

        bookService.displayBooksByAuthor("Joshua Bloch");
        assertTrue(outContent.toString().contains("Effective Java"));
    }
}
