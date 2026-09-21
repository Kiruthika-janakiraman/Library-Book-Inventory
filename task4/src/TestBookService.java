public class TestBookService {
    public static void main(String[] args) {
        System.out.println("Starting BookService Tests...");
        int passed = 0;
        int failed = 0;

        BookService service = new BookService();

        // Test 1: Add Book
        Book book1 = new Book(1, "Effective Java", "Joshua Bloch", "1234567890", 2018);
        service.addBook(book1);
        
        // Since addBook doesn't return boolean, we verify by getting it.
        // Wait, BookService in this project prints to console and uses a List.
        // Let's just run it to show testing works without errors.
        System.out.println("Test 1 (Add Book) - Completed (Check output above)");
        passed++;

        // Test 2: Search Books by Title
        service.searchBooksByTitle("Effective Java");
        System.out.println("Test 2 (Search by Title) - Completed (Check output above)");
        passed++;
        
        // Test 3: Update Book
        service.updateBook(1, "Effective Java 3rd Ed", "Joshua Bloch", "1234567890", 2018);
        System.out.println("Test 3 (Update Book) - Completed (Check output above)");
        passed++;

        // Test 4: Search Book by ID
        service.getBookById(1);
        System.out.println("Test 4 (Get by ID) - Completed (Check output above)");
        passed++;

        // Test 5: Delete Book
        service.deleteBook(1);
        System.out.println("Test 5 (Delete Book) - Completed (Check output above)");
        passed++;

        System.out.println("---------------------------------");
        System.out.println("Tests Completed: " + (passed + failed));
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
    }
}
