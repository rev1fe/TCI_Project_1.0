package managers;

public class DataItem {
    private int ID;
    private int time;
    private int pages;
    private int searchDepth;

    public DataItem(int ID, int time, int pages, int searchDepth) {
        this.ID = ID;
        this.time = time;
        this.pages = pages;
        this.searchDepth = searchDepth;
    }
}
