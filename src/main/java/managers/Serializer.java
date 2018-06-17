package managers;

import com.google.gson.Gson;
import interfaces.ISerializer;
import items.Item;

import java.util.ArrayList;
import java.util.List;

public class Serializer implements ISerializer {

    @Override
    public String listOfItemToJson(List<String> items) {
        Gson gson = new Gson();
        return gson.toJson(items);
    }

    @Override
    public String itemToJson(String item) {
        return item;
    }

}
