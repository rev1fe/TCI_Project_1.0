package crawler;

import interfaces.ICrawler;
import managers.DataItem;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Crawler implements ICrawler {
    public String baseUrl;

    private PagesCrawler pagesCrawler;
    private PageCrawler pageCrawler;

    private int searchId;
    private int searchDepth = 0;
    private int pages = 0;
    private long executionDuration;


    private List<String> allItems;

    private List<DataItem> searchDetailsList;

    public Crawler(String baseUrl) {
        this.baseUrl = baseUrl;

        this.pagesCrawler = new PagesCrawler();
        this.pageCrawler = new PageCrawler();

        allItems = new ArrayList<>();

        searchDetailsList = new ArrayList<>();
    }

    public boolean isConnected() throws IOException {
        try {
            URL url = new URL(this.baseUrl);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.connect();
            return urlConn.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            System.err.println("Error creating HTTP connection");
            e.printStackTrace();
            throw e;
        }
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

        createSearchDetail(this.searchId, executionDuration, this.pages, this.searchDepth);

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

        createSearchDetail(this.searchId, executionDuration, this.pages, this.searchDepth);

        return result;

    }

    @Override
    public DataItem getStatisticsInformation(int id) {
        return searchDetailsList.get(id);
    }

    private void createSearchDetail(int searchId, long executionDuration, int pages, int searchDepth) {
        DataItem dataItem = new DataItem(searchId, executionDuration, pages, searchDepth);
        searchDetailsList.add(dataItem);
    }

    void setPagesCrawler(PagesCrawler pagesCrawler) {
        this.pagesCrawler = pagesCrawler;
    }

    void setPageCrawler(PageCrawler pageCrawler) {
        this.pageCrawler = pageCrawler;
    }
}