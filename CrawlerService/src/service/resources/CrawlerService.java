package service.resources;

import com.google.gson.Gson;
import crawler.Crawler;
import interfaces.ICrawler;
import interfaces.ISerializer;
import managers.Serializer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("rest")
public class CrawlerService {

    private ICrawler crawler;
    private ISerializer serializer;

    public CrawlerService(ICrawler crawler, ISerializer serializer) {
        this.crawler = crawler;
        this.serializer = serializer;
    }

    //Get all
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() throws IOException {
        String allItems = serializer.listOfItemToJson(crawler.getAllItems());

            if ( allItems == null|| allItems.equals("")) {
                throw new InternalServerErrorException();
            } else {
                return Response.ok(allItems).build();
            }


    }

    //Get Specific
    @GET
    @Path("item")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getItem(@QueryParam("keyword") String keyword) throws IOException {
        if ( keyword == null|| keyword.equals("")) {
            throw new InternalServerErrorException();
        }

        String specificIdem = serializer.itemToJson(crawler.getSpecificItem(keyword));

        if ( specificIdem == null|| specificIdem.equals("")) {
            throw new InternalServerErrorException();
        } else {
            return Response.ok(specificIdem).build();
        }
    }

    //Get Statistics
    @GET
    @Path("stats")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getStatistics(@QueryParam("id") int id){
        if ( id <= 0  ) {
            throw new IllegalArgumentException();
        }

        String stats = serializer.statisticsToJson(crawler.getStatisticsInformation(id));

        if ( stats == null|| stats.equals("")) {
            throw new InternalServerErrorException();
        } else {
            return Response.ok(stats).build();
        }
    }

}
