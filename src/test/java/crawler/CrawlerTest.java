//package crawler;
//
//import junitparams.JUnitParamsRunner;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static junitparams.JUnitParamsRunner.$;
//import static org.mockito.Mockito.*;
//
//@RunWith(JUnitParamsRunner.class)
//public class CrawlerTest {
//    private static final String BASE_URL = "http://localhost:8888";
//
//    private Crawler crawler;
//
//    @Test
//    public void setUp() throws IOException {
//        Crawler crawler = new Crawler(BASE_URL);
//        String result = crawler.getSpecificItem("Elvis Forever");
//        System.out.println(result);
//        System.out.println(crawler.getSearchDetails());
//    }
//
//    @Test
//    public void crawerGetAllItems() throws IOException {
//        List<String> results = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append(String.format("itemId: %s, categoryID: %s, title: %s, ", i, i++, "Title " + i));
//            stringBuilder.append(String.format("%s: %s, ", "Key", "Value"));
//
//            results.add(stringBuilder.toString());
//        }
//
//        when(crawler.getAllItems()).thenReturn(results);
//
//        crawler.getAllItems();
//
//        verify(crawler, times(1)).getAllItems();
//    }
//}