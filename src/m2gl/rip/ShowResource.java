package m2gl.rip;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import m2gl.tvmaze.Properties;
import m2gl.tvmaze.TVMazeShow;

@Path("")
public class ShowResource {
	
	@GET
	@Path("/show/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Show getShow(@PathParam("id") long id) {
    	ObjectMapper mapper = new MyObjectMapperProvider().getContext(Properties.class);
    	try {
			return new Show(mapper.readValue(new URL("http://api.tvmaze.com/shows/"+id), TVMazeShow.class));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	throw new NotFoundException("Not found");
    }
	
	@GET
	@Path("/rand/{n}")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Show> getRand(@PathParam("n") int n) {
    	ObjectMapper mapper = new MyObjectMapperProvider().getContext(Properties.class);
    	try {
    		List<Show> showList = new ArrayList<Show>();
    		/*Iterator<JsonNode> it = StreamSupport.stream(mapper.readTree(new URL("http://api.tvmaze.com/updates/shows")).spliterator() , false)
    	            .limit(10)
    	            .iterator();*/
    		ArrayList<Integer> list = new ArrayList<>();
    	    Random random = new Random();
    	    for (int i = 0; i < n; i++) {
    	        list.add(random.nextInt(38350));
    	    }
    	    Iterator<Integer> it = list.iterator();
    		while (it.hasNext()) {
    			int i = it.next();
    			Show sh = getShow(i);
    			showList.add(sh);
    		}
    	    return showList;
    	    
		} catch (Exception e) {
			e.printStackTrace();
		}
    	throw new NotFoundException("Not found");
    }
	
}
