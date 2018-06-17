package interfaces;

import items.Item;
import managers.DataItem;

import java.util.ArrayList;
import java.util.List;

public interface ISerializer {
    String listOfItemToJson(List<String> items);
    String itemToJson(String item);
    String statisticsToJson(DataItem dataItem);
}
