package service.resources;

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

    public void setCrawler(ICrawler crawler) {
        this.crawler = crawler;
    }

    public void setSerializer(ISerializer serializer) {
        this.serializer = serializer;
    }

    //Get all
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() throws IOException {
        if(crawler == null){
            crawler = new Crawler("");
        }
        if(serializer == null){
            serializer = new Serializer();
        }

        String allItems = serializer.listOfItemToJson(crawler.getAllItems());

        if ( allItems == null|| allItems.equals("")) {
            throw new InternalServerErrorException();
        } else {
            return javax.ws.rs.core.Response.ok(allItems).build();
        }

    }

    //Get Specific
    @GET
    @Path("item")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getItem(@QueryParam("keyword") String keyword) throws IOException {
        if(crawler == null){
            crawler = new Crawler("");
        }
        if(serializer == null){
            serializer = new Serializer();
        }

        if ( keyword == null|| keyword.equals("")) {
            throw new InternalServerErrorException();
        }


        String a = crawler.getSpecificItem(keyword);
        String specificIdem = serializer.itemToJson(a);

        if ( specificIdem == null|| specificIdem.equals("")) {
            throw new InternalServerErrorException();
        } else {
            return javax.ws.rs.core.Response.ok(specificIdem).build();
        }
    }

    //Get Statistics
    @GET
    @Path("stats")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getStatistics(@QueryParam("id") int id){
        if(crawler == null){
            crawler = new Crawler("");
        }
        if(serializer == null){
            serializer = new Serializer();
        }

        if ( id < 0  ) {
            throw new IllegalArgumentException();
        }

        String stats = crawler.getSearchDetails(id);

        if ( stats == null|| stats.equals("")) {
            throw new InternalServerErrorException();
        } else {
            return javax.ws.rs.core.Response.ok(stats).build();
        }
    }

}