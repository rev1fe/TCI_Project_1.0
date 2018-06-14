package managers;

public class DataItem {
    private int queryId;
    private int timeElapsed;
    private int pagesExplored;
    private int searchDepth;

    public DataItem(int queryId, int timeElapsed, int pagesExplored, int searchDepth) {
        this.queryId = queryId;
        this.timeElapsed = timeElapsed;
        this.pagesExplored = pagesExplored;
        this.searchDepth = searchDepth;
    }

    public int getQueryId() {
        return queryId;
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }

    public int getPagesExplored() {
        return pagesExplored;
    }

    public int getSearchDepth() {
        return searchDepth;
    }
}
