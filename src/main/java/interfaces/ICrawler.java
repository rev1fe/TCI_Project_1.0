package interfaces;

import managers.DataItem;

import java.io.IOException;
import java.util.List;

public interface ICrawler {
    List<String> getCategoryLinks(String baseUrl) throws IOException;
    List<String> getCategoryItemsUrls(String categoryUrl) throws IOException;
    String getSpecificItem(String baseUrl, String name) throws IOException;
}
