package managers;

import interfaces.ISerializer;
import items.Item;

import java.util.ArrayList;

public class Serializer implements ISerializer {
    @Override
    public String listOfItemToJson(ArrayList<Item> items) {
        return null;
    }

    @Override
    public String itemToJson(Item item) {
        return null;
    }

    @Override
    public String statisticsToJson(DataItem dataItem) {
        return null;
    }
}
