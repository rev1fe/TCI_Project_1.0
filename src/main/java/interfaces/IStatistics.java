package interfaces;

import managers.DataItem;

public interface IStatistics {
    void saveDataItem(DataItem dataItem);
    DataItem getDataItem(int id);
}
