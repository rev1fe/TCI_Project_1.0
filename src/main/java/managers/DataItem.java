package managers;

public class DataItem {
    private int queryId;
    private long timeElapsed;
    private int pagesExplored;
    private int searchDepth;

    public DataItem(int queryId, long timeElapsed, int pagesExplored, int searchDepth) {
        this.queryId = queryId;
        this.timeElapsed = timeElapsed;
        this.pagesExplored = pagesExplored;
        this.searchDepth = searchDepth;
    }

    public int getQueryId() {
        return queryId;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public int getPagesExplored() {
        return pagesExplored;
    }

    public int getSearchDepth() {
        return searchDepth;
    }
}
