package m2gl.rip;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.StreamSupport;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import m2gl.tvmaze.Properties;
import m2gl.tvmaze.TVMazeShow;

@Path("")
public class ShowResource {
	
	private boolean checkGenre(Show sh, String genre) {
		List<String> genres = sh.getGenres();
		Iterator<String> it = genres.iterator();
		boolean res = false;
		while (it.hasNext()) {
			String cur = it.next();
			if (cur.equals(genre)) {
				res = true;
			}
		}
		return res;
	}
	
	private boolean checkGenres(Show sh, List<String> genres) {
		boolean res = true;
		Iterator<String> it = genres.iterator();
		while (it.hasNext()) {
			String cur = it.next();
			res = (res && checkGenre(sh, cur));
		}
		return res;
	}
	
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
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Show> search(@QueryParam("name") String name, @QueryParam("genre") List<String> genre) {
		ObjectMapper mapper = new MyObjectMapperProvider().getContext(Properties.class);
		try {
			genre = (genre != null && !genre.isEmpty()) ? genre : null;
			List<Show> showList = new ArrayList<Show>();
			Iterator<JsonNode> it = StreamSupport.stream(mapper.readTree(new URL("http://api.tvmaze.com/search/shows/?q=" + name)).spliterator() , false)
			        .iterator();
			while (it.hasNext()) {
				JsonNode j = it.next();
				Show sh = new Show(mapper.treeToValue(j.path("show"), TVMazeShow.class));
				if (genre == null || checkGenres(sh, genre)) {
					showList.add(sh);
				}
			}
			return showList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new InvalidParameterException("Invalid name provided");
	}

	@GET
	@Path("/rand/{n}")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Show> getRand(@PathParam("n") int n) {
    	try {
    		List<Show> showList = new ArrayList<Show>();
    		ArrayList<Integer> list = new ArrayList<>();
    	    Random random = new Random();
    	    int id = 0;
    	    Show sh;
    	    for (int i = 1; i < n; i++) {
    	    	//do{
	    	    //	id = random.nextInt(38350);
	    	    	sh = getShow(i);
    	    	//}while(sh.getName() == null || sh.getName() == "Not Found");
    	    	showList.add(sh);
    	    }    	  

    	    /*
 			 ArrayList<Integer> list = new ArrayList<>();
    	     for (int i = 0; i < n; i++) {
    	        list.add(random.nextInt(38350));
    	    }
    	    Iterator<Integer> it = list.iterator();
    		while (it.hasNext()) {
    			int i = it.next();
    			Show sh = getShow(i);
    			showList.add(sh);
    		}*/
    	    return showList;
    	    
		} catch (Exception e) {
			e.printStackTrace();
		}
    	throw new NotFoundException("Not found");
    }
	
}
