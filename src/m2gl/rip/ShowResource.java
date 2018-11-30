package m2gl.rip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;

@Path("")
public class ShowResource {
	
	@GET
	@Path("/show/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Show getShow(@PathParam("id") long id) {
    	ObjectMapper mapper = new MyObjectMapperProvider().getContext(Show.class);
    	try {
			return mapper.readValue("http://api.tvmaze.com/shows/"+id, Show.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return null;
    }
	
}
