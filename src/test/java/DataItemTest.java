import managers.DataItem;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataItemTest {

    @Test
    public void TestConstructor() {
        // arrange
        DataItem dataItem;
        // act
        dataItem = new DataItem(1, 5, 3, 5);
        // assert
        assertTrue(dataItem.getQueryId() == 1);
        assertTrue(dataItem.getTimeElapsed() == 5);
        assertTrue(dataItem.getPagesExplored() == 3);
        assertTrue(dataItem.getSearchDepth() == 5);
    }
}