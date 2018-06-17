import interfaces.ICrawler;
import interfaces.ISerializer;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import service.resources.CrawlerService;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;

import static junitparams.JUnitParamsRunner.$;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class CrawlerServiceTest {
    private static final String NULL_VALUE__STRING = null;
    private static final String EMPTY_VALUE__STRING = "";
    private static final String VALID_VALUE_STRING = "{ id = 1}";
    private static final String VALID_SEARCH_VALUE = "Book";
    private static final String VALID_SPECIFIC_RESULT = "{ name = Book}";

    private static final Object[] getAllOutputs(){
        return $(
                $("{ id = 2}"),
                $("{ id = 3}"),
                $("{ id = 4}")
        );
    }

    private static final Object[] getSpecificOutputs(){
        return $(
                $("{ id = 1}", "{ name = book}"),
                $("{ id = 2}","{ name = movie}"),
                $("{ id = 3}","{ name = music}")
        );
    }

    private ICrawler crawler;
    private ISerializer serializer;
    private CrawlerService crawlerService;

    @Before
    public void setUp(){
        //Arrange for most tests
        crawler = mock(ICrawler.class);
        serializer = mock(ISerializer.class);
        crawlerService = new CrawlerService(crawler, serializer);
    }

    @Test
    public void crawlerMethodForAllIsCalledOnlyOnce(){
        //arrange
        when(serializer.listOfItemToJson(crawler.getAllItems())).thenReturn(VALID_VALUE_STRING);
        //act
        crawlerService.getAll();
        //assert
        verify(crawler, times(2)).getAllItems(); //Times is to because of the when call
    }

    @Test
    public void serializerListToJsonIsCalledOnce(){
        //arrange
        when(serializer.listOfItemToJson(crawler.getAllItems())).thenReturn(VALID_VALUE_STRING);
        //act
        crawlerService.getAll();
        //assert
        verify(serializer).listOfItemToJson(crawler.getAllItems());
    }

    @Test(expected = InternalServerErrorException.class)
    public void serviceGetAllThrowsExceptionWhenResultIsNull(){
        //arrange
        when(serializer.listOfItemToJson(crawler.getAllItems())).thenReturn(NULL_VALUE__STRING);
        //act
        crawlerService.getAll();
    }

    @Test(expected = InternalServerErrorException.class)
    public void serviceGetAllThrowsExceptionWhenResultIsEmptyString(){
        //arrange
        when(serializer.listOfItemToJson(crawler.getAllItems())).thenReturn(EMPTY_VALUE__STRING);
        //act
        crawlerService.getAll();
    }

    @Test
    public void getAllReturnsProperResult(){
        //arrange
        when(serializer.listOfItemToJson(crawler.getAllItems())).thenReturn(VALID_VALUE_STRING);
        Response result;
        //act
        result = crawlerService.getAll();
        //assert
        Assert.assertEquals("The expected result is:" + VALID_VALUE_STRING + " was: " + result.toString(), VALID_VALUE_STRING, result.getEntity() );
    }

    @Test
    @Parameters(method = "getAllOutputs")
    public void getAllReturnsProperResultWithParams(String expectedOutput){
        //arrange
        when(serializer.listOfItemToJson(crawler.getAllItems())).thenReturn(expectedOutput);
        Response result;
        //act
        result = crawlerService.getAll();
        //assert
        Assert.assertEquals("The expected result is:" + expectedOutput + " was: " + result.toString(), expectedOutput, result.getEntity() );
    }

    /**
     * Begining of Tests for GetSpecific Item
     **/

    @Test
    public void crawlerMethodForSingleItemIsCalledOnlyOnce(){
        //arrange
        when(serializer.itemToJson(crawler.getSpecificItem(VALID_SEARCH_VALUE))).thenReturn(VALID_SPECIFIC_RESULT);
        //act
        crawlerService.getItem(VALID_SEARCH_VALUE);
        //assert
        verify(crawler, times(2)).getSpecificItem(VALID_SEARCH_VALUE);//Times is to because of the when call
    }

    @Test
    public void serializerGetSingleItemIsCalledOnce(){
        //arrange
        when(serializer.itemToJson(crawler.getSpecificItem(VALID_SEARCH_VALUE))).thenReturn(VALID_SPECIFIC_RESULT);
        //act
        crawlerService.getItem(VALID_SEARCH_VALUE);
        //assert
        verify(serializer).itemToJson(crawler.getSpecificItem(VALID_SEARCH_VALUE));
    }

    @Test(expected = InternalServerErrorException.class)
    public void serviceGetSpecificItemThrowsExceptionWhenResultIsNull(){
        //arrange
        when(serializer.itemToJson(crawler.getSpecificItem(VALID_SEARCH_VALUE))).thenReturn(NULL_VALUE__STRING);
        //act
        crawlerService.getAll();
    }

    @Test(expected = InternalServerErrorException.class)
    public void serviceGetSpecificItemThrowsExceptionWhenResultIsEmptyString(){
        //arrange
        when(serializer.itemToJson(crawler.getSpecificItem(VALID_SEARCH_VALUE))).thenReturn(EMPTY_VALUE__STRING);
        //act
        crawlerService.getAll();
    }

    @Test(expected = InternalServerErrorException.class)
    public void serviceGetSingleItemThrowsExceptionWhenInputIsNull(){
        //act
        crawlerService.getItem(NULL_VALUE__STRING);
    }

    @Test(expected = InternalServerErrorException.class)
    public void serviceGetSingleItemThrowsExceptionWhenInputIsEmpty(){
        //act
        crawlerService.getItem(EMPTY_VALUE__STRING);
    }

    @Test
    public void getSpecificItemReturnsProperResult(){
        //arrange
        when(serializer.itemToJson(crawler.getSpecificItem(VALID_SEARCH_VALUE))).thenReturn(VALID_SPECIFIC_RESULT);
        Response result;
        //act
        result = crawlerService.getItem(VALID_SEARCH_VALUE);
        //assert
        Assert.assertEquals("The expected result is:" + VALID_SPECIFIC_RESULT + " was: " + result.toString(), VALID_SPECIFIC_RESULT, result.getEntity() );
    }

    @Test
    @Parameters(method = "getSpecificOutputs")
    public void getSpecificReturnsProperResultWithParams(String indirectInput, String expectedOutput){
        //arrange
        when(serializer.itemToJson(crawler.getSpecificItem(indirectInput))).thenReturn(expectedOutput);
        Response result;
        //act
        result = crawlerService.getItem(indirectInput);
        //assert
        Assert.assertEquals("The expected result is:" + expectedOutput + " was: " + result.toString(), expectedOutput, result.getEntity() );
    }

    /**
     * Begining of Get Statistics tests
     */



}
