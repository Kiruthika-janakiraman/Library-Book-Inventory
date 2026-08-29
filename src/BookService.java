import java.util.ArrayList;

public class BookService {

    private final ArrayList<Book> books = new ArrayList<>();

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
            for (Book book : books) {
                displayBook(book);
            }
        }
    }

    // Read - Find a book by ID
    public void getBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                System.out.println("Book found:");
                displayBook(book);
                return;
            }
        }

        System.out.println("Book with ID " + id + " not found.");
    }

    // Update - Update book details
    public void updateBook(int id, String title, String author,
                           String isbn, int publicationYear) {

        for (Book book : books) {
            if (book.getId() == id) {

                book.setTitle(title);
                book.setAuthor(author);
                book.setIsbn(isbn);
                book.setPublicationYear(publicationYear);

                System.out.println("Book updated successfully:");
                displayBook(book);
                return;
            }
        }

        System.out.println("Book with ID " + id + " not found.");
    }

    // Delete - Remove a book by ID
    public void deleteBook(int id) {

        for (int i = 0; i < books.size(); i++) {

            if (books.get(i).getId() == id) {

                Book deletedBook = books.remove(i);

                System.out.println(
                        "Book deleted successfully: " + deletedBook.getTitle()
                );

                return;
            }
        }

        System.out.println("Book with ID " + id + " not found.");
    }

    // Search books by title
    public void searchBooksByTitle(String title) {

        boolean found = false;

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                displayBook(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println(
                    "No books found with title containing '" + title + "'."
            );
        }
    }

    // Search books by author
    public void searchBooksByAuthor(String author) {

        boolean found = false;

        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                displayBook(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println(
                    "No books found with author containing '" + author + "'."
            );
        }
    }

    // Search books by publication year
    public void searchBooksByPublicationYear(int publicationYear) {

        boolean found = false;

        for (Book book : books) {
            if (book.getPublicationYear() == publicationYear) {
                displayBook(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println(
                    "No books found published in the year '" + publicationYear + "'."
            );
        }
    }

    // Search books by ISBN
    public void searchBooksByIsbn(String isbn) {

        boolean found = false;

        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                displayBook(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found with ISBN '" + isbn + "'.");
        }
    }

    // Display books by exact author name
    public void displayBooksByAuthor(String author) {

        boolean found = false;

        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                displayBook(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found by author '" + author + "'.");
        }
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