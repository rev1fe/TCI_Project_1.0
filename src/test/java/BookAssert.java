import items.Book;
import org.fest.assertions.api.AbstractAssert;
import org.fest.assertions.api.Assertions;



public class BookAssert extends AbstractAssert<BookAssert, Book> {
    public BookAssert(Book actual) {
        super(actual, BookAssert.class);
    }

    public static BookAssert assertThat(Book actual) {
        return new BookAssert(actual);
    }

    //Specific assertion for author
    public BookAssert hasAuthor(String name) {
        // check that actual Book we want to make assertions on is not null.
        isNotNull();

        // use of existing Fest assertions but replacing the error message (format specifier are supported)
        Assertions.assertThat(actual.getAuthor())
                .overridingErrorMessage("Expected books's author to be <%s> but was <%s>", name, actual.getAuthor())
                .isEqualTo(name);

        // another option : throwing directly an assertion error if the expected condition is not met
        String errorMessage = String.format("Expected book's author to be <%s> but was <%s>", name, actual.getAuthor());
        if (!actual.getAuthor().equals(name)) { throw new AssertionError(errorMessage); }

        return this; // return the current assertion for method chaining
    }

    //Another specific assertion !
    public BookAssert hasPublisher(String publisher) {
        // check that actual Book we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        Assertions.assertThat(actual.getPublisher())
                .overridingErrorMessage("Expected book's publisher to be <%s> but was <%s>", publisher, actual.getPublisher())
                .isEqualTo(publisher);

        return this; // return the current assertion for method chaining
    }

    //Another specific assertion !
    public BookAssert hasIsbn(String Isbn) {
        // check that actual Book we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        Assertions.assertThat(actual.getIsbn())
                .overridingErrorMessage("Expected book's ISBN to be <%s> but was <%s>", Isbn, actual.getIsbn())
                .isEqualTo(Isbn);

        return this; // return the current assertion for method chaining
    }
}
