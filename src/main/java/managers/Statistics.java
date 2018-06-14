package managers;

import interfaces.IStatistics;

import java.util.ArrayList;

public class Statistics implements IStatistics{
    private ArrayList<DataItem> dataItems;

    public Statistics() {
        dataItems = new ArrayList<>();
    }

    @Override
    public void saveDataItem(int id, int timeElapsed, int pagesExplored, int searchDepth) {
        DataItem item = new DataItem(id, timeElapsed, pagesExplored, searchDepth);
        dataItems.add(item);
    }

    @Override
    public DataItem getDataItem(int id) {
        for (DataItem item : dataItems ) {
            if(id == item.getQueryId()){
                return item;
            }
        }
        return null;
    }
}
