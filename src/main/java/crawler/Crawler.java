package crawler;

import interfaces.ICrawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Crawler implements ICrawler {
    private String baseUrl;

    private PagesCrawler pagesCrawler;
    private PageCrawler pageCrawler;

    private int searchId;
    private int searchDepth;

    public List<String> allItems;

    public Crawler(String baseUrl) {
        this.baseUrl = baseUrl;

        this.pagesCrawler = new PagesCrawler();
        this.pageCrawler = new PageCrawler();

        allItems = new ArrayList<>();
    }

    @Override
    public List<String> getAllItems() throws IOException {
        // Increase search id
        this.searchId++;

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
        return allItems;
    }

    @Override
    public String getItemDetailsByTitle(String name) throws IOException {
        this.searchId++;
        return pagesCrawler.getSpecificItem(baseUrl, name);
    }

}
