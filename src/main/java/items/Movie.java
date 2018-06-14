package items;

import java.util.ArrayList;

public class Movie extends Item{
    private String director;
    private String genre;
    private ArrayList<String> writers;
    private ArrayList<String> stars;

    public Movie(String name, String format, int year, String director, String genre, ArrayList<String> writers, ArrayList<String> stars) {
        super(name, format, year);
        this.director = director;
        this.genre = genre;
        this.writers = writers;
        this.stars = stars;
    }
}
