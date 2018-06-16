package interfaces;

import items.Item;
import managers.DataItem;

import java.util.ArrayList;
import java.util.List;

public interface ISerializer {
    String listOfItemToJson(List<String> items);
    String itemToJson(Item item);
    String statisticsToJson(DataItem dataItem);
}
