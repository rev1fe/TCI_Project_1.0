package crawler;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class CrawlerTest {

    @Test
    public void test() throws IOException {
        Crawler c = new Crawler("http://localhost:8888");
        String items = c.getItemDetailsByTitle("No");
        System.out.println(items);
    }

}