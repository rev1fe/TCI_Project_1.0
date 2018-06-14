package interfaces;

import managers.DataItem;

import java.io.IOException;
import java.util.List;

public interface ICrawler {
    List<String> getAllItems() throws IOException;
    String getItemDetailsByTitle(String name) throws IOException;
}
