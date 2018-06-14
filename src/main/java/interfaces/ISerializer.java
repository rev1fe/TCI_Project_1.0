package interfaces;

import items.Item;
import managers.DataItem;

import java.util.ArrayList;

public interface ISerializer {
    String listOfItemToJson(ArrayList<Item> items);
    String itemToJson(Item item);
    String statisticsToJson(DataItem dataItem);
}
