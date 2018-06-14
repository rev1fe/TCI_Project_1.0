package crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Crawler {
    private String baseUrl;

    private PagesCrawler pagesCrawler;
    private PageCrawler pageCrawler;

    public Crawler(String baseUrl) {
        this.baseUrl = baseUrl;

        this.pagesCrawler = new PagesCrawler();
        this.pageCrawler = new PageCrawler();
    }

    public List<String> getAllItems() throws IOException {
        // Get categories links
        List<String> categories = pagesCrawler.getCategoryLinks(baseUrl);

        // Get all items on all pages
        List<String> items = new ArrayList<>();
        for(String categoryUrl : categories) {
            items.addAll(pagesCrawler.getCategoryItemsUrls(categoryUrl));
        }

        return items;
    }

    public String getSpecificItem(String itemUrl) throws IOException {
        return pageCrawler.getItemDate(itemUrl);
    }


}
