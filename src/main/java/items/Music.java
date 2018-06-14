package items;

public class Music extends Item{
    private String artist;

    public Music(String name, String format, int year, String artist) {
        super(name, format, year);
        this.artist = artist;
    }
}
