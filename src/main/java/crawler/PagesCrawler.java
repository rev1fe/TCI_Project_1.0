package crawler;

import interfaces.ICrawler;
import managers.DataItem;
import managers.Statistics;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PagesCrawler implements ICrawler {

    private Queue<String> queue;
    private Queue<String> itemsQueue;

    public PagesCrawler() {
        queue = new LinkedList<String>();
        itemsQueue = new LinkedList<>();
    }

    @Override
    public List<String> getCategoryLinks(String baseUrl) throws IOException {
        List<String> results = new ArrayList<>();

        Document doc = Jsoup.connect(baseUrl).get();
        // Get links of categories
        Elements categoryLinks = doc.select("ul.nav li a");
        for(Element link : categoryLinks) {
            // Add links to queue
            results.add(link.attr("abs:href"));
        }

        return results;
    }

    /*
    * Returns a list of links of items of a category
    * */
    @Override
    public List<String> getCategoryItemsUrls(String categoryUrl) throws IOException {
        List<String> results = new ArrayList<>();

        Document doc = Jsoup.connect(categoryUrl).get();

        Elements itemsUrls = doc.select("ul.items li a");
        for(Element itemUrl : itemsUrls) {
            // Add item links to queue
            results.add(itemUrl.attr("abs:href"));
        }

        return results;
    }

    @Override
    public String getSpecificItem(String baseUrl, String name) {
        StringBuilder toBeSerialized = new StringBuilder();

        queue.clear();
        itemsQueue.clear();

        long startTime = System.currentTimeMillis();

        int numberOfPagesSearched = 0;

        try {
            Document doc = Jsoup.connect(baseUrl).get();
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

                while (!itemsQueue.isEmpty()) {
                    String itemUrl = itemsQueue.remove();


                    String itemId = itemUrl.substring(itemUrl.length() - 3);
                    String categoryId = itemId.substring(0, 1);

                    numberOfPagesSearched++;

                    doc = Jsoup.connect(itemUrl).get();

                    String title = doc.select(".media-details h1").text();

                    if(!title.equals(name)) {
                        continue;
                    }

                    // Add title to toBeSerialized
                    toBeSerialized.append(String.format("itemId: %s, categoryID: %s, title: %s, ", itemId, categoryId, title));

                    Elements tableElements =  doc.select("table tbody > tr");

                    for (Element tableElement: tableElements) {
                        String key = tableElement.select("th").text();
                        String value = tableElement.select("td").text();

                        toBeSerialized.append(String.format("%s: %s, ", key, value));
                    }

                    break OUTER;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long totalTime = System.currentTimeMillis() - startTime;

        return toBeSerialized.toString();
    }
}
