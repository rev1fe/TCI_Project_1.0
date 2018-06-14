package crawler;

import interfaces.ICrawler;
import managers.DataItem;
import managers.Statistics;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Crawler implements ICrawler {
    private Document doc;

    private Queue<String> queue;
    private Queue<String> itemsQueue;

    public static int searchId = 0;

    public Crawler() {
        queue = new LinkedList<String>();
        itemsQueue = new LinkedList<>();
    }


    @Override
    public List<String> getAllItems() {
        List<String> results = new ArrayList<>();

        searchId++;

        queue.clear();
        itemsQueue.clear();

        long startTime = System.currentTimeMillis();

        final String BASE_URL = "http://localhost:8888/";
        int numberOfPagesSearched = 0;

        try {
            Document doc = Jsoup.connect(BASE_URL).get();
            // Get links of categories
            Elements categoryLinks = doc.select("ul.nav li a");
            for(Element link : categoryLinks) {
                // Add links to queue
                queue.add(link.attr("abs:href"));
            }

            while(!queue.isEmpty()) {
                String url = queue.remove();
                numberOfPagesSearched++;

                doc = Jsoup.connect(url).get();

                Elements itemsUrls = doc.select("ul.items li a");
                for(Element itemUrl : itemsUrls) {
                    // Add item links to queue
                    itemsQueue.add(itemUrl.attr("abs:href"));
                }

                while (!itemsQueue.isEmpty()) {
                    String itemUrl = itemsQueue.remove();

                    StringBuilder toBeSerialized = new StringBuilder();

                    String itemId = itemUrl.substring(itemUrl.length() - 3);
                    String categoryId = itemId.substring(0, 1);

                    numberOfPagesSearched++;

                    doc = Jsoup.connect(itemUrl).get();

                    String title = doc.select(".media-details h1").text();

                    // Add title to toBeSerialized
                    toBeSerialized.append(String.format("itemId: %s, categoryID: %s, title: %s, ", itemId, categoryId, title));

                    Elements tableElements =  doc.select("table tbody > tr");

                    for (Element tableElement: tableElements) {
                        String key = tableElement.select("th").text();
                        String value = tableElement.select("td").text();

                        toBeSerialized.append(String.format("%s: %s, ", key, value));

                        results.add(toBeSerialized.toString());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long totalTime = System.currentTimeMillis() - startTime;

        Statistics statistics = new Statistics();

        // Send data to serializer
        parseAllItems(results);

        return results;
    }

    @Override
    public String getSpecificItem(String name) {
        StringBuilder toBeSerialized = new StringBuilder();
        boolean found = false;

        searchId++;

        queue.clear();
        itemsQueue.clear();

        long startTime = System.currentTimeMillis();

        final String BASE_URL = "http://localhost:8888/";
        int numberOfPagesSearched = 0;

        try {
            Document doc = Jsoup.connect(BASE_URL).get();
            // Get links of categories
            Elements categoryLinks = doc.select("ul.nav li a");
            for(Element link : categoryLinks) {
                // Add links to queue
                queue.add(link.attr("abs:href"));
            }

            OUTER: while(!queue.isEmpty()) {
                String url = queue.remove();
                numberOfPagesSearched++;

                doc = Jsoup.connect(url).get();

                Elements itemsUrls = doc.select("ul.items li a");
                for(Element itemUrl : itemsUrls) {
                    // Add item links to queue
                    itemsQueue.add(itemUrl.attr("abs:href"));
                }

                INNER: while (!itemsQueue.isEmpty()) {
                    String itemUrl = itemsQueue.remove();


                    String itemId = itemUrl.substring(itemUrl.length() - 3);
                    String categoryId = itemId.substring(0, 1);

                    numberOfPagesSearched++;

                    doc = Jsoup.connect(itemUrl).get();

                    String title = doc.select(".media-details h1").text();

                    if(title.equals(name)) {
                        found = true;
                    }

                    // Add title to toBeSerialized
                    toBeSerialized.append(String.format("itemId: %s, categoryID: %s, title: %s, ", itemId, categoryId, title));

                    Elements tableElements =  doc.select("table tbody > tr");

                    for (Element tableElement: tableElements) {
                        String key = tableElement.select("th").text();
                        String value = tableElement.select("td").text();

                        toBeSerialized.append(String.format("%s: %s, ", key, value));

                        if(found) {
                            break OUTER;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long totalTime = System.currentTimeMillis() - startTime;

        return toBeSerialized.toString();
    }

    @Override
    public DataItem getStatisticsInformation(int id) {
        return null;
    }

    private String parseAllItems(List<String> allItems) {
        return "";
    }

    private String parseItem(String item) {
        return "";
    }
}
