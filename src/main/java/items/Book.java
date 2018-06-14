package items;

public class Book extends Item {
    private String genre;
    private String author;
    private String publisher;
    private String isbn;

    public Book(String name, String format, int year, String genre, String author, String publisher, String isbn) {
        super(name, format, year);
        this.genre = genre;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIsbn() {
        return isbn;
    }
}
