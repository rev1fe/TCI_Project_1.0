import items.Book;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;

@RunWith(JUnitParamsRunner.class)
public class BookTest {
    //String name, String format, int year, String genre, String author, String publisher, String isbn
    private static final String VALID_BOOK_NAME = "GameOfThrones";
    private static final String VALID_BOOK_FORMAT = "Book";
    private static final int VALID_BOOK_YEAR = 1195;
    private static final String VALID_BOOK_GENRE = "Fantasy";

    private static final String VALID_BOOK_AUTHOR = "George Martin";
    private static final String VALID_BOOK_PUBLISHER = "Orange";
    private static final String VALID_BOOK_ISBN = "123-123-123";

    private static final String VALID_BOOK_AUTHOR_TWO = "Stephen King";
    private static final String VALID_BOOK_PUBLISHER_TWO = "Van Pier";
    private static final String VALID_BOOK_ISBN_TWO = "222-222-222";

    private static final String VALID_BOOK_AUTHOR_THREE = "JK Rowling";
    private static final String VALID_BOOK_PUBLISHER_THREE = "Apple";
    private static final String VALID_BOOK_ISBN_THREE = "333-333-333";


    private static final Object[] getBooks(){
        return $(
                $(new Book(VALID_BOOK_NAME, VALID_BOOK_FORMAT, VALID_BOOK_YEAR, VALID_BOOK_GENRE, VALID_BOOK_AUTHOR, VALID_BOOK_PUBLISHER, VALID_BOOK_ISBN)),
                $(new Book(VALID_BOOK_NAME, VALID_BOOK_FORMAT, VALID_BOOK_YEAR, VALID_BOOK_GENRE, VALID_BOOK_AUTHOR_TWO, VALID_BOOK_PUBLISHER_TWO, VALID_BOOK_ISBN_TWO)),
                $(new Book(VALID_BOOK_NAME, VALID_BOOK_FORMAT, VALID_BOOK_YEAR, VALID_BOOK_GENRE, VALID_BOOK_AUTHOR_THREE, VALID_BOOK_PUBLISHER_THREE, VALID_BOOK_ISBN_THREE))
        );
    }

    private Book book;

    @Before
    public void setUp(){
        book = new Book(VALID_BOOK_NAME, VALID_BOOK_FORMAT, VALID_BOOK_YEAR, VALID_BOOK_GENRE, VALID_BOOK_AUTHOR, VALID_BOOK_PUBLISHER, VALID_BOOK_ISBN);
    }

    @Test
    public void bookShouldHaveAuthor() {
        //ARRANGE
        //ACT
        //ASSERT
        BookAssert.assertThat(book).hasAuthor(VALID_BOOK_AUTHOR);
    }

    @Test
    public void bookShouldHavePublisher() {
        //ARRANGE
        //ACT
        //ASSERT
        BookAssert.assertThat(book).hasPublisher(VALID_BOOK_PUBLISHER);
    }

    @Test
    public void bookShouldHaveIsbn() {
        //ARRANGE
        //ACT
        //ASSERT
        BookAssert.assertThat(book).hasIsbn(VALID_BOOK_ISBN);
    }

    @Test
    @Parameters(method = "getBooks")
    public void canCreateBookWithAllParams(Book book){
        Assert.assertNotNull(book.getAuthor());
        Assert.assertNotNull(book.getPublisher());
        Assert.assertNotNull(book.getIsbn());
    }

}
