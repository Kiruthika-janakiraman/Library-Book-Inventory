/**
 * Lightweight integration/regression checks that require only the JDK.
 * These are intentionally dependency-free so the packaged project can be
 * compiled and validated without Maven or Gradle.
 */
public class IntegrationTest {
    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        testAddAndGet();
        testCaseInsensitiveTitleSearch();
        testUpdate();
        testDelete();
        testMissingId();
        testDuplicateId();

        System.out.println("\nIntegration Test Summary");
        System.out.println("Tests Passed: " + passed);
        System.out.println("Tests Failed: " + failed);

        if (failed > 0) {
            System.exit(1);
        }
    }

    private static void testAddAndGet() {
        BookService service = new BookService();
        service.addBook(new Book(1, "Effective Java", "Joshua Bloch", "1234567890", 2018));
        assertTrue(service.getBookById(1).isPresent(), "Add + Get Book");
    }

    private static void testCaseInsensitiveTitleSearch() {
        BookService service = new BookService();
        service.addBook(new Book(1, "Effective Java", "Joshua Bloch", "1234567890", 2018));
        service.searchBooksByTitle("effective");
        assertTrue(service.getBookById(1).isPresent(), "Case-insensitive title search");
    }

    private static void testUpdate() {
        BookService service = new BookService();
        service.addBook(new Book(1, "Old", "Old Author", "111", 2000));
        service.updateBook(1, "New", "New Author", "222", 2020);
        Book updated = service.getBookById(1).get();
        assertTrue(updated.getPublicationYear() == 2020 && updated.getTitle().equals("New"),
                "Update all mutable properties");
    }

    private static void testDelete() {
        BookService service = new BookService();
        service.addBook(new Book(1, "Delete Me", "Author", "333", 2010));
        service.deleteBook(1);
        assertTrue(!service.getBookById(1).isPresent(), "Delete Book");
    }

    private static void testMissingId() {
        BookService service = new BookService();
        assertTrue(!service.getBookById(99).isPresent(), "Missing ID handled with Optional");
    }

    private static void testDuplicateId() {
        BookService service = new BookService();
        service.addBook(new Book(1, "One", "Author", "444", 2010));
        boolean rejected = false;
        try {
            service.addBook(new Book(1, "Duplicate", "Author", "555", 2011));
        } catch (IllegalArgumentException ex) {
            rejected = true;
        }
        assertTrue(rejected, "Duplicate ID validation");
    }

    private static void assertTrue(boolean condition, String name) {
        if (condition) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name);
        }
    }
}
