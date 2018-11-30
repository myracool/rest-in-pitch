package m2gl.rip;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import m2gl.tvmaze.Properties;
import m2gl.tvmaze.TVMazeShow;

@Path("")
public class ShowResource {
	
	@GET
	@Path("/show/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Show getMap(@PathParam("id") long id) {
    	ObjectMapper mapper = new MyObjectMapperProvider().getContext(Properties.class);
    	try {
			return new Show(mapper.readValue(new URL("http://api.tvmaze.com/shows/"+id), TVMazeShow.class));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	throw new NotFoundException("User not found");
    }
	
}
