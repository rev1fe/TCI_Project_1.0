package interfaces;

import managers.DataItem;

import java.util.List;

public interface ICrawler {
    List<String> getAllItems();
    String getSpecificItem(String name);
    DataItem getStatisticsInformation(int id);
}
