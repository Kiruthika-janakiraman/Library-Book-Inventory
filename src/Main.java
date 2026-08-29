import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BookService bookService = new BookService();

        boolean running = true;

        while (running) {

            System.out.println("\n========== LIBRARY BOOK INVENTORY ==========");
            System.out.println("1. Add Book");
            System.out.println("2. List All Books");
            System.out.println("3. Find Book by ID");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Search Book by Title");
            System.out.println("7. Search Book by Author");
            System.out.println("8. Search Book by Publication Year");
            System.out.println("9. Search Book by ISBN");
            System.out.println("10. Exit");
            System.out.println("============================================");

            System.out.print("Enter your choice: ");

            try {

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    // Create - Add a new book
                    case 1:

                        System.out.println("\n---------- ADD BOOK ----------");

                        System.out.print("Enter Book ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        String title = getNonEmptyString(scanner, "Enter Book Title: ");
                        String author = getNonEmptyString(scanner, "Enter Author Name: ");
                        String isbn = getNonEmptyString(scanner, "Enter ISBN: ");
                        int publicationYear = getValidYear(scanner, "Enter Publication Year: ");

                        Book book = new Book(
                                id,
                                title,
                                author,
                                isbn,
                                publicationYear
                        );

                        bookService.addBook(book);

                        break;

                    // Read - Display all books
                    case 2:

                        System.out.println("\n---------- ALL BOOKS ----------");

                        bookService.getAllBooks();

                        break;

                    // Read - Find book by ID
                    case 3:

                        System.out.println("\n---------- FIND BOOK ----------");

                        System.out.print("Enter Book ID: ");
                        int searchId = scanner.nextInt();
                        scanner.nextLine();

                        bookService.getBookById(searchId);

                        break;

                    // Update - Update existing book
                    case 4:

                        System.out.println("\n---------- UPDATE BOOK ----------");

                        System.out.print("Enter Book ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();

                        String newTitle = getNonEmptyString(scanner, "Enter New Title: ");
                        String newAuthor = getNonEmptyString(scanner, "Enter New Author: ");
                        String newIsbn = getNonEmptyString(scanner, "Enter New ISBN: ");
                        int newPublicationYear = getValidYear(scanner, "Enter New Publication Year: ");

                        bookService.updateBook(
                                updateId,
                                newTitle,
                                newAuthor,
                                newIsbn,
                                newPublicationYear
                        );

                        break;

                    // Delete - Delete book by ID
                    case 5:

                        System.out.println("\n---------- DELETE BOOK ----------");

                        System.out.print("Enter Book ID to delete: ");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine();

                        bookService.deleteBook(deleteId);

                        break;

                    // Search by title
                    case 6:

                        System.out.println("\n---------- SEARCH BY TITLE ----------");

                        System.out.print("Enter book title: ");
                        String searchTitle = scanner.nextLine();

                        bookService.searchBooksByTitle(searchTitle);

                        break;

                    // Search by author
                    case 7:

                        System.out.println("\n---------- SEARCH BY AUTHOR ----------");

                        System.out.print("Enter author name: ");
                        String searchAuthor = scanner.nextLine();

                        bookService.searchBooksByAuthor(searchAuthor);

                        break;

                    // Search by publication year
                    case 8:

                        System.out.println("\n---------- SEARCH BY PUBLICATION YEAR ----------");

                        System.out.print("Enter publication year: ");
                        int searchYear = scanner.nextInt();
                        scanner.nextLine();

                        bookService.searchBooksByPublicationYear(searchYear);

                        break;

                    // Search by ISBN
                    case 9:

                        System.out.println("\n---------- SEARCH BY ISBN ----------");

                        System.out.print("Enter ISBN: ");
                        String searchIsbn = scanner.nextLine();

                        bookService.searchBooksByIsbn(searchIsbn);

                        break;

                    // Exit
                    case 10:

                        running = false;

                        System.out.println(
                                "\nThank you for using Library Book Inventory!"
                        );

                        break;

                    default:

                        System.out.println(
                                "Invalid choice. Please enter a number between 1 and 10."
                        );
                }

            } catch (Exception e) {

                System.out.println(
                        "Invalid input! Please enter the correct data type."
                );

                scanner.nextLine();
            }
        }

        scanner.close();
    }

    public static String getNonEmptyString(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                break;
            }
            System.out.println("Error: Input cannot be empty. Please try again.");
        }
        return input;
    }

    public static int getValidYear(Scanner scanner, String prompt) {
        int year = 0;
        boolean validYear = false;
        int currentYear = java.time.Year.now().getValue();
        while (!validYear) {
            System.out.print(prompt);
            try {
                year = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (year > 0 && year <= currentYear) {
                    validYear = true;
                } else {
                    System.out.println("Error: Please enter a valid year between 1 and " + currentYear + ".");
                }
            } catch (Exception e) {
                System.out.println("Error: Invalid year format. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        return year;
    }
}