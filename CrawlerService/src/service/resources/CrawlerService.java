package service.resources;

import com.google.gson.Gson;
import crawler.Crawler;
import interfaces.ICrawler;
import interfaces.ISerializer;
import managers.Serializer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

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
    public Response getAll(){
        String gamesOfTheDay = serializer.listOfItemToJson(crawler.getAllItems());

            if ( gamesOfTheDay == null|| gamesOfTheDay.equals("")) {
                throw new InternalServerErrorException();
            } else {
                return Response.ok(gamesOfTheDay).build();
            }


    }



}
