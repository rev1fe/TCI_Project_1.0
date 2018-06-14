package managers;

import interfaces.IStatistics;

import java.util.ArrayList;

public class Statistics implements IStatistics{
    private ArrayList<DataItem> dataItems;

    public Statistics() {
        dataItems = new ArrayList<>();
    }

    @Override
    public void saveDataItem(DataItem dataItem) {

    }

    @Override
    public DataItem getDataItem(int id) {
        return null;
    }
}
