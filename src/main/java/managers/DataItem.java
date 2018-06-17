package managers;

public class DataItem {
    private int id;
    private long time;
    private int pages;
    private int searchDepth;

    public DataItem(int id, long time, int pages, int searchDepth) {
        this.id = id;
        this.time = time;
        this.pages = pages;
        this.searchDepth = searchDepth;
    }

    @Override
    public String toString() {
        return String.format("id: %s, time: %s, pages: %s, searchDepth: %s", this.id, this.time, this.pages, this.searchDepth);
    }

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getSearchDepth() {
        return searchDepth;
    }

    public void setSearchDepth(int searchDepth) {
        this.searchDepth = searchDepth;
    }
}
