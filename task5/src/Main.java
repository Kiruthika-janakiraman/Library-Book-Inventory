import java.util.Scanner;

/**
 * Console entry point and presentation/controller layer.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BookService service = new BookService();

    public static void main(String[] args) {
        seedDemoData();
        runApplication();
    }

    private static void seedDemoData() {
        service.addBook(new Book(101, "Clean Code", "Robert C. Martin", "9780132350884", 2008));
        service.addBook(new Book(102, "Effective Java", "Joshua Bloch", "9780134685991", 2018));
    }

    private static void runApplication() {
        boolean running = true;
        System.out.println("\n========================================");
        System.out.println(" LIBRARY BOOK INVENTORY SYSTEM");
        System.out.println("========================================");

        while (running) {
            displayMenu();
            int choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 1: handleAddBook(); break;
                    case 2: service.listBooks(); break;
                    case 3: service.searchBooksByTitle(readNonEmpty("Enter title to search: ")); break;
                    case 4: service.searchBooksByAuthor(readNonEmpty("Enter author to search: ")); break;
                    case 5: service.searchBooksByPublicationYear(readInt("Enter publication year: ")); break;
                    case 6: service.searchBooksByIsbn(readNonEmpty("Enter ISBN to search: ")); break;
                    case 7: handleGetBook(); break;
                    case 8: handleUpdateBook(); break;
                    case 9: service.deleteBook(readInt("Enter Book ID to delete: ")); break;
                    case 10:
                        running = false;
                        System.out.println("Application closed.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select 1-10.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Input error: " + ex.getMessage());
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n1. Add Book");
        System.out.println("2. List All Books");
        System.out.println("3. Search by Title");
        System.out.println("4. Search by Author");
        System.out.println("5. Search by Publication Year");
        System.out.println("6. Search by ISBN");
        System.out.println("7. Get Book by ID");
        System.out.println("8. Update Book");
        System.out.println("9. Delete Book");
        System.out.println("10. Exit");
    }

    private static void handleAddBook() {
        int id = readInt("Enter Book ID: ");
        String title = readNonEmpty("Enter Title: ");
        String author = readNonEmpty("Enter Author: ");
        String isbn = readNonEmpty("Enter ISBN: ");
        int year = readInt("Enter Publication Year: ");
        service.addBook(new Book(id, title, author, isbn, year));
    }

    private static void handleGetBook() {
        int id = readInt("Enter Book ID: ");
        service.getBookById(id).ifPresentOrElse(
            book -> System.out.println(book),
            () -> System.out.println("Book with ID " + id + " not found.")
        );
    }

    private static void handleUpdateBook() {
        int id = readInt("Enter Book ID to update: ");
        String title = readNonEmpty("Enter new Title: ");
        String author = readNonEmpty("Enter new Author: ");
        String isbn = readNonEmpty("Enter new ISBN: ");
        int year = readInt("Enter new Publication Year: ");
        service.updateBook(id, title, author, isbn, year);
    }

    private static String readNonEmpty(String prompt) {
        System.out.print(prompt);
        String value = scanner.nextLine().trim();
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be empty.");
        }
        return value;
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        String value = scanner.nextLine().trim();
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Please enter a valid integer.");
        }
    }
}
