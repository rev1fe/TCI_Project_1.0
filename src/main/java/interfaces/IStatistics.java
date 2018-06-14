package interfaces;

import managers.DataItem;

public interface IStatistics {
    void saveDataItem(int id, int timeElapsed, int pagesExplored, int searchDepth);
    DataItem getDataItem(int id);
}
