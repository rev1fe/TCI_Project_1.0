package crawler;

import interfaces.ICrawler;
import managers.DataItem;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Crawler implements ICrawler {
    private String baseUrl;

    private PagesCrawler pagesCrawler;
    private PageCrawler pageCrawler;

    private int searchId;
    private int searchDepth;
    private int pages = 0;
    private long executionDuration;


    private List<String> allItems;

    public Crawler(String baseUrl) {
        this.baseUrl = "http://localhost:8888";

        this.pagesCrawler = new PagesCrawler();
        this.pageCrawler = new PageCrawler();

        allItems = new ArrayList<>();
    }

    @Override
    public List<String> getAllItems() throws IOException {
        // Increase search id
        this.searchId++;

        // Time start
        long startTime = System.currentTimeMillis();

        // Get categories links
        List<String> categories = pagesCrawler.getCategoryLinks(baseUrl);

        // Loop through categories
        for(String categoryUrl : categories) {
            // Get category items urls
            List<String> categoryItems = pagesCrawler.getCategoryItemsUrls(categoryUrl);
            // Loop through category items urls
            for (String categoryItemUrl : categoryItems) {
                // Add category item to list
                allItems.add(pageCrawler.getItemData(categoryItemUrl));
            }
        }

        // Set searchDepth
        searchDepth = allItems.size();

        // Time end
        long endTime = System.currentTimeMillis();

        // Calculate execution time
        executionDuration = (endTime - startTime);

        return allItems;
    }

    @Override
    public String getSpecificItem(String name) throws IOException {
        this.searchId++;

        // Time start
        long startTime = System.currentTimeMillis();

        String result = pagesCrawler.getSpecificItem(baseUrl, name);

        // Time end
        long endTime = System.currentTimeMillis();

        // Calculate execution time
        executionDuration = (endTime - startTime);

        return result;

    }

    @Override
    public String getSearchDetails() {
        return new DataItem(searchId, executionDuration, pages, searchDepth).toString();
    }
}
