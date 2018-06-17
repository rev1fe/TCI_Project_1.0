package crawler;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import sun.jvm.hotspot.utilities.Assert;

import java.io.IOException;
import java.util.List;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class CrawlerTest {
    private static final String VALID_URL = "http://localhost:8888";
    private static final List<String> VALID_ANSWER = null;
    private static final int INVALID_ID = -11;
    private static final String VALID_NAME = "LOTR";
    private static final String VALID_RESPONSE = "{ name = LOTR}";

    private Crawler crawler;
    private PageCrawler pageCrawler;
    private PagesCrawler pagesCrawler;

    private static final Object[] getSpecific(){
        return $(
                $("http://localhost:8888", "Batman", "{ name = Batman}"),
                $( "http://localhost:8888", "Superman", "{ name = Superman}"),
                $("http://localhost:8888", "Spiderman", "{ name = Spiderman}")
        );
    }

    @Before
    public void setUP(){
        pageCrawler = mock(PageCrawler.class);
        pagesCrawler = mock(PagesCrawler.class);
        crawler = new Crawler(VALID_URL);
        crawler.setPageCrawler(pageCrawler);
        crawler.setPagesCrawler(pagesCrawler);
    }

    @Test(expected = NullPointerException.class)
    public void getAllUsesPagesCrawlerGetCatLinks() throws IOException {
        when(pagesCrawler.getCategoryLinks(VALID_URL)).thenReturn(VALID_ANSWER);
        crawler.getAllItems();
        verify(pagesCrawler).getCategoryLinks(VALID_URL);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void throwsExceptionWhenInvalidID() throws IOException {
        crawler.getStatisticsInformation(INVALID_ID);
    }

    @Test
    public void getSpecificItemCallsPagesCrawler() throws IOException {
        when(pagesCrawler.getSpecificItem(VALID_URL, VALID_NAME)).thenReturn(VALID_RESPONSE);
        crawler.getSpecificItem(VALID_NAME);
        verify(pagesCrawler).getSpecificItem(VALID_URL, VALID_NAME);
    }

    @Test
    @Parameters(method = "getSpecific")
    public void getSpecificItemWithParams(String url , String name, String response) throws IOException {
        when(pagesCrawler.getSpecificItem(url, name)).thenReturn(response);
        crawler.getSpecificItem(name);
        verify(pagesCrawler).getSpecificItem(url, name);
    }

    @Test
    public void testUrlIsSameWhenSetup() {
        assertEquals(crawler.baseUrl, VALID_URL);
    }


    @Test
    public void testConnection() throws IOException {
        assertTrue(crawler.isConnected());
    }


}