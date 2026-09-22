/**
 * Domain model representing a library book.
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;

    public Book(int id, String title, String author, String isbn, int publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getPublicationYear() { return publicationYear; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }

    @Override
    public String toString() {
        return "Book ID: " + id + System.lineSeparator()
             + "Title: " + title + System.lineSeparator()
             + "Author: " + author + System.lineSeparator()
             + "ISBN: " + isbn + System.lineSeparator()
             + "Publication Year: " + publicationYear;
    }
}
