import static org.junit.Assert.*;

import items.Movie;
import org.junit.Test;

import java.util.ArrayList;

public class MovieTest {

    private String name = "TestMovie";
    private String format = "DVD";
    private int year = 2018;
    private String director = "Bram";
    private String genre = "Educational";
    private ArrayList<String> writers = new ArrayList<>();
    private ArrayList<String> stars = new ArrayList<>();


    @Test
    public void TestConstructor() {
        // arrange
        Movie movie;
        writers.add("Bram");
        stars.add("Bram");
        // act
        movie = new Movie(name, format, year, genre, director, writers, stars);
        // assert
        assertTrue(movie.getName().contains("TestMovie"));
        assertTrue(movie.getGenre().contains("Educational"));
        assertTrue(movie.getFormat().contains("DVD"));
        assertTrue(movie.getYear() == 2018);
        assertTrue(movie.getDirector().contains("Bram"));
        assertTrue(movie.getWriters().contains("Bram"));
        assertTrue(movie.getStars().contains("Bram"));
    }
}