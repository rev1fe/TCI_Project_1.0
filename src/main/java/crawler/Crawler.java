package crawler;

import interfaces.ICrawler;
import items.Item;
import managers.DataItem;

import java.util.ArrayList;

public class Crawler implements ICrawler {
    @Override
    public ArrayList<Item> getAllItems() {
        return null;
    }

    @Override
    public Item getSpecificItem(String name) {
        return null;
    }

    @Override
    public DataItem getStatisticsInformation(int id) {
        return null;
    }
}
