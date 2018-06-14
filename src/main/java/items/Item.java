package items;

public abstract class Item {
    private String name;
    private String format;
    private int year;

    public Item(String name, String format, int year) {
        this.name = name;
        this.format = format;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getFormat() {
        return format;
    }

    public int getYear() {
        return year;
    }
}
