import managers.Statistics;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatisticsTest {

    @Test
    public void TestDataItemList() {
        // arrange
        Statistics statistic = new Statistics();
        // act
        statistic.saveDataItem(1, 5, 3, 5);
        // assert
        assertTrue(statistic.getDataItem(1).getQueryId() == 1);
        assertTrue(statistic.getDataItem(1).getTimeElapsed() == 5);
        assertTrue(statistic.getDataItem(1).getPagesExplored() == 3);
        assertTrue(statistic.getDataItem(1).getSearchDepth() == 5);
    }
}