package interfaces;

import items.Item;
import managers.DataItem;

import java.util.ArrayList;
import java.util.List;

public interface ICrawler {
    List<String> getAllItems();
    String getSpecificItem(String name);
    DataItem getStatisticsInformation(int id);
}
