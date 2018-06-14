package crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class PageCrawler {

    public String getItemDate(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        StringBuilder result = new StringBuilder();


        String title = doc.select(".media-details h1").text();
        String itemId = url.substring(url.length() - 3);
        String categoryId = itemId.substring(0, 1);

        // Add title to toBeSerialized
        result.append(String.format("itemId: %s, categoryID: %s, title: %s, ", itemId, categoryId, title));

        Elements tableElements =  doc.select("table tbody > tr");

        for (Element tableElement: tableElements) {
            String key = tableElement.select("th").text();
            String value = tableElement.select("td").text();

            result.append(String.format("%s: %s, ", key, value));
        }

        return result.toString();
    }
}
