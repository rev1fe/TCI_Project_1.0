package interfaces;

import items.Item;
import managers.DataItem;

import java.util.ArrayList;

public interface ICrawler {
    ArrayList<Item> getAllItems();
    Item getSpecificItem(String name);
    DataItem getStatisticsInformation(int id);
}
