package items;

import java.util.ArrayList;

public class Movie extends Item{
    private String genre;
    private String director;
    private ArrayList<String> writers;
    private ArrayList<String> stars;

    public Movie(String name, String format, int year, String genre, String director, ArrayList<String> writers, ArrayList<String> stars) {
        super(name, format, year);
        this.genre = genre;
        this.director = director;
        this.writers = writers;
        this.stars = stars;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public ArrayList<String> getWriters() {
        return writers;
    }

    public ArrayList<String> getStars() {
        return stars;
    }
}
